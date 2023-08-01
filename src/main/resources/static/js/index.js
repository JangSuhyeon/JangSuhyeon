$(function () {

    // 댓글 저장하기 버튼 클릭 이벤트
    $('#cmtFrmBtn').click(function (e) {
        e.preventDefault();

        const name = $.trim($('#cmtFrmName').val());
        const comment = $.trim($('#cmtFrmComment').val());

        if (name === '' || name === null) {
            alert("이름을 입력해주세요.");
        }else if (comment === '' || comment === null) {
            alert('댓글 내용을 입력해주세요.');
        }else {
            $.ajax({
                type:'POST',
                url:'/comment',
                data:$('#cmtFrm').serialize(),
                success:function (res) {
                    console.log(res);
                },
                error() {
                    alert("댓글 저장에 실패했습니다.");
                },
            })
        }
    })
})