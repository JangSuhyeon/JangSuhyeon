$(function () {

    // 댓글 저장하기 버튼 클릭 이벤트
    $('#cmtFrmBtn').click(function (e) {
        e.preventDefault();

        const name = $.trim($('#cmtFrmName').val());
        const password = $.trim($('#cmtFrmPassword').val());
        const comment = $.trim($('#cmtFrmComment').val());

        if (name === '' || name === null) {
            alert("이름을 입력해주세요.");
        }else if (password === '' || comment === null) {
            alert('비밀번호를 입력해주세요.');
        }else if (comment === '' || comment === null) {
            alert('댓글 내용을 입력해주세요.');
        }else {
            $.ajax({
                type:'POST',
                url:'/comment',
                data:$('#cmtFrm').serialize(),
                success:function (res) {
                    console.log(res);
                    /*Todo 댓글 새로 로드*/
                },
                error() {
                    alert("댓글 저장에 실패했습니다.");
                },
            })
        }
    })
})