$(function(){
    $('article').sortable({
        connectWith: "article",
        scroll: false,
        helper:"clone",
        receive: function(e, ui){
            let no = $(ui.item).attr('data-no');
            let value = $(this).attr('data-status');

            console.log("no : " + no);
            console.log("value : " + value);
        }
    });

    $('#btnAdd').click(function(){
        let value = $('input[name=todo]').val();
        let item = `<div class='item'>
                        <button class = "del"> X </button>
                         <em class = 'tit'>#200</em>
                         <p>내용</p>
                         <span class = 'date'>2023-03-01</span>
                      </div>`;

    $('.ready').append(item);
    });

    $(document).on('click', '.del', function(){
        $(this).parent().remove();

    });
});