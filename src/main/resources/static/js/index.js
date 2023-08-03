$(function () {

    /*// 메뉴 클릭 시 활성화
    $('.nav-link').click(function () {
        $('.nav-link').removeClass('active');
        $(this).addClass('active');
    })*/

    function onScroll() {
        const scrollPosition = $(window).scrollTop();
        const sectionElements = $('[id^="section"]');
        sectionElements.each(function() {
            const section = $(this);
            const topOffset = section.offset().top - 400;
            const bottomOffset = topOffset + section.outerHeight();
            if (scrollPosition >= topOffset && scrollPosition < bottomOffset) {
                const sectionId = section.attr('id');
                $(".nav-link").each(function() {
                    if ($(this).attr('href') === `#${sectionId}`) {
                        $(".nav-link").removeClass('active');
                        $(this).addClass('active');
                    }
                });
            }
        });
    }

    $(window).on('scroll', onScroll);

    // 댓글 스와이프 새로고침
    function ReloadComment() {
        $.ajax({
            type:'GET',
            url:'/comment',
            success:function (res) {
                var commentListDiv = $('#commentList');

                // 기존 댓글 초기화
                commentListDiv.empty();
                $(".testimonial-carousel").trigger('destroy.owl.carousel');

                // 댓글 목록 출력
                res.forEach(function(comment) {
                    var commentDiv = $('<div>').addClass('testimonial-item rounded p-4');
                    var quoteIcon = $('<i>').addClass('fa fa-quote-left fa-2x text-primary mb-3');
                    var commentContent = $('<p>').text(comment.cmtContent);
                    var profileImg = $('<img>').addClass('img-fluid flex-shrink-0 rounded-circle').attr('src', comment.cmtGb == '0' ? 'img/emoji.png' : 'img/testimonial-1.jpg');
                    var profileInfoDiv = $('<div>').addClass('ps-3');
                    var commenterName = $('<h6>').text(comment.cmtName);
                    var commenterRole = $('<small>').text(comment.cmtGb == '0' ? 'Guest' : 'Member');

                    profileInfoDiv.append(commenterName, commenterRole);
                    commentDiv.append(quoteIcon, commentContent, $('<div>').addClass('d-flex align-items-center').append(profileImg, profileInfoDiv));
                    commentListDiv.append(commentDiv);
                });

                // 스와이프 초기화
                $(".testimonial-carousel").owlCarousel({
                    autoplay: true,
                    smartSpeed: 1000,
                    center: true,
                    dots: false,
                    loop: true,
                    nav : true,
                    navText : [
                        '<i class="bi bi-chevron-left"></i>',
                        '<i class="bi bi-chevron-right"></i>'
                    ],
                    responsive: {
                        0:{
                            items:1
                        },
                        576:{
                            items:1
                        },
                        768:{
                            items:2
                        },
                        992:{
                            items:3
                        }
                    }
                });
            },
            error() {
                alert('댓글 새로고침에 실패했습니다.');
            }
        })
    }

    // 댓글 저장하기 버튼 클릭 이벤트
    $('#cmtFrmBtn').click(function (e) {
        e.preventDefault();

        const name = $.trim($('#cmtFrmName').val());
        const content = $.trim($('#cmtFrmContent').val());
        $('#cmtFrmGb').val('0'); // Todo GUEST:0, MEMBER:1

        if (name === '' || name === null) {
            alert("이름을 입력해주세요.");
        }else if (content === '' || content === null) {
            alert('댓글 내용을 입력해주세요.');
        }else {
            $.ajax({
                type:'POST',
                url:'/comment',
                data:$('#cmtFrm').serialize(),
                success:function (res) {
                    alert("댓글이 저장되었습니다!");
                    ReloadComment(); // 댓글 스와이프 새로고침
                },
                error() {
                    alert("댓글 저장에 실패했습니다.");
                },
            })
        }
    })
})