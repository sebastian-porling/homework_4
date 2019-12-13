$(document).ready(function() {
    $('select').change(function () {
        $('form').submit();
    });
    $('input').change(function () {
        $('form').submit();
    });

    $('form').submit(function(e) {
        e.preventDefault();
        $.ajax({
            url: '/convert',
            data: $(this).serialize(),
            success: function(data)
            {
                var elements = $(data);
                var found = elements.find('#result');
                $('#result').val(found.val());
                var error = elements.find('.is-error')
                $('.is-error').html(error.html());
            }
        });

    });
});