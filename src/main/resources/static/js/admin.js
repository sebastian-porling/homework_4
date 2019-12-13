$(document).ready(function() {
    $('select').change(function (e) {
        e.preventDefault();
        var code = $(this).children("option:selected").val();
        $("tbody tr").each(function(index){
            var val = $(":nth-child(2)", this).html();
            if(val == code) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    });
    $('button').click(function (e) {
        e.preventDefault();
        if ($(this).html() == "EDIT") {
            $(this).parent().parent().children(":nth-child(4)").children().attr("readonly", false);
            $(this).removeClass("is-primary");
            $(this).addClass("is-warning");
            $(this).html('SAVE');
        } else {
            $(this).parent().parent().children(":nth-child(4)").children().attr("readonly", true);
            $(this).removeClass("is-warning");
            $(this).addClass("is-primary");
            $(this).html('EDIT');
            var id = $(this).parent().parent().children(":nth-child(1)").html();
            var rate = $(this).parent().parent().children(":nth-child(4)").children().val();
            var url = '/admin/changeRate/?rate=' + id + "&newRate=" + rate ;
            $.ajax({
                url: url,
                success: function (data) {
                    var elements = $(data);
                    var found = elements.find('.is-error')
                    $('.is-error').html(found.html());
                }
            });
        }

    });
});