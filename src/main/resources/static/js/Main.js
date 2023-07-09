/**
 * 
 */
 
var header = $("meta[name='_csrf_header']").attr('content');
var token = $("meta[name='_csrf']").attr('content');
// var userNickname = $('#userNicknameTag').val();
/**
 * ajax를 이용하여
 * 로그인시 해당 유저의 아이디를 표시
 */
$("#userNickname").ready(function(){
  	$.ajax({
    type : 'GET',           // 타입 (get, post, put 등등)
    url : '/user/username'           // 요청할 서버url
    , beforeSend: function(xhr){
        xhr.setRequestHeader(header, token);
    }
    , success : function(result) { // 결과 성공 콜백함수
        console.log(result);
        console.log(result.username);
        console.log(location.host);
        var nicknameTag = $("#userNickname");
        nicknameTag.text(result.username)
        $('#userNicknameTag').val(result.username);
        
        chatName();
        
    },
    })
    
})

var ws;
var userNickname = $('#userNicknameTag').val();

function wsOpen(){
	ws = new WebSocket("ws://localhost:8080/chatting");
	wsEvt();
}

function wsEvt(){
	ws.onopen = function(data){
		console.log("onopen data : ", data)
	}
	
	ws.onmessage = function(data){
		console.log("onmessage data : ", data)
		var msg = data.data;
		console.log("msg : " , msg)
		console.log("trim : " , msg.trim())
		if(msg != null && msg.trim() != ''){
			// 1차 기본코드
			// $("#chattingLog").append("<p>" + msg + "</p>");
			
			// 2차 textarea 값 변경 코드
			// var chatLog = $("#chattingLog").val();
			// $("#chattingLog").val(chatLog+msg+"\r");
			
			// 3차 textarea append 코드
			$("#chattingLog").append(msg+"\r");
			
			// 스크롤을 맨 아래로 변환
			var scroll = $('#chattingLog').prop('scrollHeight');
  			$('#chattingLog').scrollTop(scroll);
		}
	}
	
	var chatInputTag = document.getElementById("chatInput")
	
	chatInputTag.addEventListener("keypress", function(e){
		if(e.keyCode == 13){ // ENTER 누를 시 send() 실행
			send();
		}
	});
}


function chatName(){
	userNickname = $('#userNicknameTag').val();
	if(userNickname == null || userNickname.trim() == ""){
		alert("사용자 이름을 입력해주세요.");
		$("#userNickname").focus();
	} else{
		wsOpen();
	}
}

function send(){
	var msg = $("#chatInput").val();
	ws.send(userNickname+" : "+msg);
	$("#chatInput").val("");
}