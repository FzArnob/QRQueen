package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.google.appinventor.components.runtime.ReplForm;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.webrtc.DataChannel;
import org.webrtc.IceCandidate;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaStream;
import org.webrtc.PeerConnection;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.RtpReceiver;
import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;

public class WebRTCNativeMgr {
    private static final boolean DEBUG = true;
    private static final String LOG_TAG = "KodularWebRTC";
    /* access modifiers changed from: private */
    public static final CharsetDecoder utf8Decoder = Charset.forName("UTF-8").newDecoder();
    /* access modifiers changed from: private */
    public DataChannel dataChannel;
    DataChannel.Observer dataObserver;
    /* access modifiers changed from: private */
    public boolean first;
    /* access modifiers changed from: private */
    public ReplForm form;
    /* access modifiers changed from: private */
    public volatile boolean haveLocalDescription;
    private boolean haveOffer;
    private List<PeerConnection.IceServer> iceServers;
    /* access modifiers changed from: private */
    public volatile boolean keepPolling;
    PeerConnection.Observer observer;
    /* access modifiers changed from: private */
    public PeerConnection peerConnection;
    /* access modifiers changed from: private */
    public String rCode;
    /* access modifiers changed from: private */
    public Random random;
    private String rendezvousServer;
    /* access modifiers changed from: private */
    public String rendezvousServer2;
    SdpObserver sdpObserver;
    /* access modifiers changed from: private */
    public TreeSet<String> seenNonces = new TreeSet<>();
    Timer timer;

    public WebRTCNativeMgr(String str, String str2) {
        this.haveOffer = false;
        this.keepPolling = true;
        this.haveLocalDescription = false;
        this.first = true;
        this.random = new Random();
        this.dataChannel = null;
        this.rendezvousServer = null;
        this.rendezvousServer2 = null;
        this.iceServers = new ArrayList();
        this.timer = new Timer();
        this.sdpObserver = new SdpObserver() {
            public final void onSetFailure(String str) {
            }

            public final void onSetSuccess() {
            }

            public final void onCreateFailure(String str) {
                Log.d(WebRTCNativeMgr.LOG_TAG, "onCreateFailure: ".concat(String.valueOf(str)));
            }

            public final void onCreateSuccess(SessionDescription sessionDescription) {
                try {
                    Log.d(WebRTCNativeMgr.LOG_TAG, "sdp.type = " + sessionDescription.type.canonicalForm());
                    Log.d(WebRTCNativeMgr.LOG_TAG, "sdp.description = " + sessionDescription.description);
                    new DataChannel.Init();
                    if (sessionDescription.type == SessionDescription.Type.OFFER) {
                        Log.d(WebRTCNativeMgr.LOG_TAG, "Got offer, about to set remote description (again?)");
                        WebRTCNativeMgr.this.peerConnection.setRemoteDescription(WebRTCNativeMgr.this.sdpObserver, sessionDescription);
                    } else if (sessionDescription.type == SessionDescription.Type.ANSWER) {
                        Log.d(WebRTCNativeMgr.LOG_TAG, "onCreateSuccess: type = ANSWER");
                        WebRTCNativeMgr.this.peerConnection.setLocalDescription(WebRTCNativeMgr.this.sdpObserver, sessionDescription);
                        boolean unused = WebRTCNativeMgr.this.haveLocalDescription = true;
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put(CommonProperties.TYPE, "answer");
                        jSONObject.put("sdp", sessionDescription.description);
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("offer", jSONObject);
                        WebRTCNativeMgr.this.sendRendezvous(jSONObject2);
                    }
                } catch (Exception e) {
                    Log.e(WebRTCNativeMgr.LOG_TAG, "Exception during onCreateSuccess", e);
                }
            }
        };
        this.observer = new PeerConnection.Observer() {
            public final void onAddStream(MediaStream mediaStream) {
            }

            public final void onAddTrack(RtpReceiver rtpReceiver, MediaStream[] mediaStreamArr) {
            }

            public final void onIceCandidatesRemoved(IceCandidate[] iceCandidateArr) {
            }

            public final void onIceConnectionChange(PeerConnection.IceConnectionState iceConnectionState) {
            }

            public final void onIceConnectionReceivingChange(boolean z) {
            }

            public final void onRemoveStream(MediaStream mediaStream) {
            }

            public final void onRenegotiationNeeded() {
            }

            public final void onDataChannel(DataChannel dataChannel) {
                Log.d(WebRTCNativeMgr.LOG_TAG, "Have Data Channel!");
                Log.d(WebRTCNativeMgr.LOG_TAG, "v5");
                DataChannel unused = WebRTCNativeMgr.this.dataChannel = dataChannel;
                dataChannel.registerObserver(WebRTCNativeMgr.this.dataObserver);
                boolean unused2 = WebRTCNativeMgr.this.keepPolling = false;
                WebRTCNativeMgr.this.timer.cancel();
                Log.d(WebRTCNativeMgr.LOG_TAG, "Poller() Canceled");
                WebRTCNativeMgr.this.seenNonces.clear();
            }

            public final void onIceCandidate(IceCandidate iceCandidate) {
                try {
                    Log.d(WebRTCNativeMgr.LOG_TAG, "IceCandidate = " + iceCandidate.toString());
                    if (iceCandidate.sdp == null) {
                        Log.d(WebRTCNativeMgr.LOG_TAG, "IceCandidate is null");
                    } else {
                        Log.d(WebRTCNativeMgr.LOG_TAG, "IceCandidateSDP = " + iceCandidate.sdp);
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("nonce", WebRTCNativeMgr.this.random.nextInt(100000));
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("candidate", iceCandidate.sdp);
                    jSONObject2.put("sdpMLineIndex", iceCandidate.sdpMLineIndex);
                    jSONObject2.put("sdpMid", iceCandidate.sdpMid);
                    jSONObject.put("candidate", jSONObject2);
                    WebRTCNativeMgr.this.sendRendezvous(jSONObject);
                } catch (Exception e) {
                    Log.e(WebRTCNativeMgr.LOG_TAG, "Exception during onIceCandidate", e);
                }
            }

            public final void onIceGatheringChange(PeerConnection.IceGatheringState iceGatheringState) {
                Log.d(WebRTCNativeMgr.LOG_TAG, "onIceGatheringChange: iceGatheringState = ".concat(String.valueOf(iceGatheringState)));
            }

            public final void onSignalingChange(PeerConnection.SignalingState signalingState) {
                Log.d(WebRTCNativeMgr.LOG_TAG, "onSignalingChange: signalingState = ".concat(String.valueOf(signalingState)));
            }
        };
        this.dataObserver = new DataChannel.Observer() {
            public final void onBufferedAmountChange(long j) {
            }

            public final void onStateChange() {
            }

            public final void onMessage(DataChannel.Buffer buffer) {
                try {
                    String charBuffer = WebRTCNativeMgr.utf8Decoder.decode(buffer.data).toString();
                    Log.d(WebRTCNativeMgr.LOG_TAG, "onMessage: received: ".concat(String.valueOf(charBuffer)));
                    WebRTCNativeMgr.this.form.evalScheme(charBuffer);
                } catch (CharacterCodingException e) {
                    Log.e(WebRTCNativeMgr.LOG_TAG, "onMessage decoder error", e);
                }
            }
        };
        this.rendezvousServer = str;
        try {
            JSONObject jSONObject = new JSONObject((str2.isEmpty() || str2.startsWith("OK")) ? "{\"rendezvous2\" : \"rendezvous.creator.kodular.io\",\"iceservers\" : [{ \"server\" : \"turn:turn.creator.kodular.io:3478\",\"username\" : \"kodular\",\"password\" : \"yxUJKuyWzUW6R94uz96jZnAvBpzJSavb\"}]}" : str2);
            this.rendezvousServer2 = jSONObject.getString("rendezvous2");
            JSONArray jSONArray = jSONObject.getJSONArray("iceservers");
            this.iceServers = new ArrayList(jSONArray.length());
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                PeerConnection.IceServer.Builder builder = PeerConnection.IceServer.builder(jSONObject2.getString("server"));
                Log.d(LOG_TAG, "Adding iceServer = " + jSONObject2.getString("server"));
                if (jSONObject2.has("username")) {
                    builder.setUsername(jSONObject2.getString("username"));
                }
                if (jSONObject2.has("password")) {
                    builder.setPassword(jSONObject2.getString("password"));
                }
                this.iceServers.add(builder.createIceServer());
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "parsing iceServers:", e);
        }
    }

    public void initiate(ReplForm replForm, Context context, String str) {
        this.form = replForm;
        this.rCode = str;
        PeerConnectionFactory.initializeAndroidGlobals(context, false);
        PeerConnectionFactory peerConnectionFactory = new PeerConnectionFactory(new PeerConnectionFactory.Options());
        PeerConnection.RTCConfiguration rTCConfiguration = new PeerConnection.RTCConfiguration(this.iceServers);
        rTCConfiguration.continualGatheringPolicy = PeerConnection.ContinualGatheringPolicy.GATHER_CONTINUALLY;
        this.peerConnection = peerConnectionFactory.createPeerConnection(rTCConfiguration, new MediaConstraints(), this.observer);
        this.timer.schedule(new TimerTask() {
            public final void run() {
                WebRTCNativeMgr.this.Poller();
            }
        }, 0, 1000);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x01ce A[Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void Poller() {
        /*
            r11 = this;
            java.lang.String r0 = "nonce"
            java.lang.String r1 = "candidate"
            java.lang.String r2 = "KodularWebRTC"
            boolean r3 = r11.keepPolling     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            if (r3 != 0) goto L_0x000b
            return
        L_0x000b:
            java.lang.String r3 = "Poller() Called"
            android.util.Log.d(r2, r3)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r4 = "Poller: rendezvousServer2 = "
            r3.<init>(r4)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r4 = r11.rendezvousServer2     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            r3.append(r4)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            android.util.Log.d(r2, r3)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            org.apache.http.impl.client.DefaultHttpClient r3 = new org.apache.http.impl.client.DefaultHttpClient     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            r3.<init>()     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            org.apache.http.client.methods.HttpGet r4 = new org.apache.http.client.methods.HttpGet     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r6 = "https://"
            r5.<init>(r6)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r6 = r11.rendezvousServer     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            r5.append(r6)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r6 = "/rendezvous2/"
            r5.append(r6)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r6 = r11.rCode     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            r5.append(r6)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r6 = "-s"
            r5.append(r6)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            r4.<init>(r5)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            org.apache.http.HttpResponse r3 = r3.execute(r4)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            r4.<init>()     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            r5 = 0
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch:{ all -> 0x01cb }
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch:{ all -> 0x01cb }
            org.apache.http.HttpEntity r3 = r3.getEntity()     // Catch:{ all -> 0x01cb }
            java.io.InputStream r3 = r3.getContent()     // Catch:{ all -> 0x01cb }
            r7.<init>(r3)     // Catch:{ all -> 0x01cb }
            r6.<init>(r7)     // Catch:{ all -> 0x01cb }
        L_0x0068:
            java.lang.String r3 = r6.readLine()     // Catch:{ all -> 0x01c8 }
            if (r3 == 0) goto L_0x0072
            r4.append(r3)     // Catch:{ all -> 0x01c8 }
            goto L_0x0068
        L_0x0072:
            r6.close()     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            boolean r3 = r11.keepPolling     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            if (r3 != 0) goto L_0x007f
            java.lang.String r0 = "keepPolling is false, we're done!"
            android.util.Log.d(r2, r0)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            return
        L_0x007f:
            java.lang.String r3 = r4.toString()     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r4 = "response = "
            java.lang.String r5 = java.lang.String.valueOf(r3)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r4 = r4.concat(r5)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            android.util.Log.d(r2, r4)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r4 = ""
            boolean r4 = r3.equals(r4)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            if (r4 == 0) goto L_0x009e
            java.lang.String r0 = "Received an empty response"
            android.util.Log.d(r2, r0)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            return
        L_0x009e:
            org.json.JSONArray r4 = new org.json.JSONArray     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            r4.<init>(r3)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r5 = "jsonArray.length() = "
            r3.<init>(r5)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            int r5 = r4.length()     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            r3.append(r5)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            android.util.Log.d(r2, r3)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            r3 = 0
        L_0x00b9:
            int r5 = r4.length()     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            if (r3 >= r5) goto L_0x01c2
            java.lang.String r5 = "i = "
            java.lang.String r6 = java.lang.String.valueOf(r3)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r5 = r5.concat(r6)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            android.util.Log.d(r2, r5)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r6 = "element = "
            r5.<init>(r6)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r6 = r4.optString(r3)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            r5.append(r6)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            android.util.Log.d(r2, r5)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.Object r5 = r4.get(r3)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            org.json.JSONObject r5 = (org.json.JSONObject) r5     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            boolean r6 = r11.haveOffer     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r7 = "offer"
            r8 = 1
            if (r6 != 0) goto L_0x0150
            boolean r6 = r5.has(r7)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            if (r6 != 0) goto L_0x00f7
        L_0x00f4:
            int r3 = r3 + 1
            goto L_0x00b9
        L_0x00f7:
            java.lang.Object r3 = r5.get(r7)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            org.json.JSONObject r3 = (org.json.JSONObject) r3     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r5 = "sdp"
            java.lang.String r5 = r3.optString(r5)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r6 = "type"
            java.lang.String r3 = r3.optString(r6)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            r11.haveOffer = r8     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r6 = "sdb = "
            java.lang.String r7 = java.lang.String.valueOf(r5)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r6 = r6.concat(r7)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            android.util.Log.d(r2, r6)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r6 = "type = "
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r3 = r6.concat(r3)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            android.util.Log.d(r2, r3)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r3 = "About to set remote offer"
            android.util.Log.d(r2, r3)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r3 = "Got offer, about to set remote description (maincode)"
            android.util.Log.d(r2, r3)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            org.webrtc.PeerConnection r3 = r11.peerConnection     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            org.webrtc.SdpObserver r6 = r11.sdpObserver     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            org.webrtc.SessionDescription r7 = new org.webrtc.SessionDescription     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            org.webrtc.SessionDescription$Type r9 = org.webrtc.SessionDescription.Type.OFFER     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            r7.<init>(r9, r5)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            r3.setRemoteDescription(r6, r7)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            org.webrtc.PeerConnection r3 = r11.peerConnection     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            org.webrtc.SdpObserver r5 = r11.sdpObserver     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            org.webrtc.MediaConstraints r6 = new org.webrtc.MediaConstraints     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            r6.<init>()     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            r3.createAnswer(r5, r6)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r3 = "createAnswer returned"
            android.util.Log.d(r2, r3)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            r3 = -1
            goto L_0x01bf
        L_0x0150:
            boolean r6 = r5.has(r0)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            if (r6 == 0) goto L_0x01bf
            boolean r6 = r11.haveLocalDescription     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            if (r6 != 0) goto L_0x0160
            java.lang.String r0 = "Incoming candidate before local description set, punting"
            android.util.Log.d(r2, r0)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            return
        L_0x0160:
            boolean r6 = r5.has(r7)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            if (r6 == 0) goto L_0x016f
            int r3 = r3 + 1
            java.lang.String r5 = "skipping offer, already processed"
            android.util.Log.d(r2, r5)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            goto L_0x00b9
        L_0x016f:
            boolean r6 = r5.isNull(r1)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            if (r6 == 0) goto L_0x0177
            goto L_0x00f4
        L_0x0177:
            java.lang.String r6 = r5.optString(r0)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.Object r5 = r5.get(r1)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            org.json.JSONObject r5 = (org.json.JSONObject) r5     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r7 = r5.optString(r1)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r9 = "sdpMid"
            java.lang.String r9 = r5.optString(r9)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r10 = "sdpMLineIndex"
            int r5 = r5.optInt(r10)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.util.TreeSet<java.lang.String> r10 = r11.seenNonces     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            boolean r10 = r10.contains(r6)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            if (r10 != 0) goto L_0x01bf
            java.util.TreeSet<java.lang.String> r10 = r11.seenNonces     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            r10.add(r6)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r6 = "new nonce, about to add candidate!"
            android.util.Log.d(r2, r6)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r6 = "candidate = "
            java.lang.String r10 = java.lang.String.valueOf(r7)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r6 = r6.concat(r10)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            android.util.Log.d(r2, r6)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            org.webrtc.IceCandidate r6 = new org.webrtc.IceCandidate     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            r6.<init>(r9, r5, r7)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            org.webrtc.PeerConnection r5 = r11.peerConnection     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            r5.addIceCandidate(r6)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            java.lang.String r5 = "addIceCandidate returned"
            android.util.Log.d(r2, r5)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
        L_0x01bf:
            int r3 = r3 + r8
            goto L_0x00b9
        L_0x01c2:
            java.lang.String r0 = "exited loop"
            android.util.Log.d(r2, r0)     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
            return
        L_0x01c8:
            r0 = move-exception
            r5 = r6
            goto L_0x01cc
        L_0x01cb:
            r0 = move-exception
        L_0x01cc:
            if (r5 == 0) goto L_0x01d1
            r5.close()     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
        L_0x01d1:
            throw r0     // Catch:{ IOException -> 0x0200, JSONException -> 0x01e9, Exception -> 0x01d2 }
        L_0x01d2:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "Caught Exception: "
            r1.<init>(r3)
            java.lang.String r3 = r0.toString()
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            android.util.Log.e(r2, r1, r0)
            return
        L_0x01e9:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "Caught JSONException: "
            r1.<init>(r3)
            java.lang.String r3 = r0.toString()
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            android.util.Log.e(r2, r1, r0)
            return
        L_0x0200:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "Caught IOException: "
            r1.<init>(r3)
            java.lang.String r3 = r0.toString()
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            android.util.Log.e(r2, r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.WebRTCNativeMgr.Poller():void");
    }

    /* access modifiers changed from: private */
    public void sendRendezvous(final JSONObject jSONObject) {
        AsynchUtil.runAsynchronously(new Runnable() {
            public final void run() {
                try {
                    jSONObject.put("first", WebRTCNativeMgr.this.first);
                    jSONObject.put("webrtc", true);
                    JSONObject jSONObject = jSONObject;
                    jSONObject.put("key", WebRTCNativeMgr.this.rCode + "-r");
                    if (WebRTCNativeMgr.this.first) {
                        boolean unused = WebRTCNativeMgr.this.first = false;
                        jSONObject.put("apiversion", Build.VERSION.SDK_INT);
                    }
                    DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("https://" + WebRTCNativeMgr.this.rendezvousServer2 + "/rendezvous2/");
                    try {
                        Log.d(WebRTCNativeMgr.LOG_TAG, "About to send = " + jSONObject.toString());
                        httpPost.setEntity(new StringEntity(jSONObject.toString()));
                        defaultHttpClient.execute(httpPost);
                    } catch (IOException e) {
                        Log.e(WebRTCNativeMgr.LOG_TAG, "sendRedezvous IOException", e);
                    }
                } catch (Exception e2) {
                    Log.e(WebRTCNativeMgr.LOG_TAG, "Exception in sendRendezvous", e2);
                }
            }
        });
    }

    public void send(String str) {
        try {
            if (this.dataChannel == null) {
                Log.w(LOG_TAG, "No Data Channel in Send");
                return;
            }
            this.dataChannel.send(new DataChannel.Buffer(ByteBuffer.wrap(str.getBytes("UTF-8")), false));
        } catch (UnsupportedEncodingException e) {
            Log.e(LOG_TAG, "While encoding data to send to companion", e);
        }
    }
}
