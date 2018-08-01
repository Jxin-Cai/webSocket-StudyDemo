let stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#notice").html("");
}

function connect() {
	let origin = $("#origin").val();
	let socket = new SockJS('/endpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        //通过动态拼接监听的终点的url实现点对点通讯
        stompClient.subscribe('/chat/single/'+origin, function (result) {
        	showContent(JSON.parse(result.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
	let body = JSON.stringify({'content': $("#content").val(), 'target':$("#target").val(), 'origin':$("#origin").val()});
    stompClient.send("/app/v3/single/chat", {}, body);

    showContent(JSON.parse(body));
    
}

function showContent(body) {
    let content = body.content;
    let time = body.time?new Date(body.time) : new Date();
    $("#notice").append("<tr><td>" + content + "</td> <td>"+time.toLocaleString()+"</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

