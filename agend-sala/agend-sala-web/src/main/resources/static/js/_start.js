var main = {};

main.defineDefaultLoader = function () {
    $.ajaxPrefilter((options, _, jqXHR) => {
        main.toggleScroll();
        main.toggleLoader();
        jqXHR.done(() => {
            main.toggleScroll();
            main.toggleLoader();
        }).fail(() => {
            main.toggleScroll();
            main.toggleLoader();
        });
    });
}

main.defineReponsiveNavToggle = function () {
    $('#nav-toggler').click(function () {
        $('#nav-to-toggle').toggleClass('is-active');
        $('#nav-toggler').toggleClass('is-active');
    });
}

main.toggleScroll = function () {
    let windowEvents = jQuery._data(window, "events");
    if (!!windowEvents && !!windowEvents.mousewheel) {
        $(window).unbind("mousewheel");
    } else {
        $(window).bind("mousewheel", function (event) {
            event.preventDefault();
        });
    }
}

main.toggleLoader = function () {
    $('.is-loader')
        .toggleClass('is-loading')
        .toggleClass('is-disabled');
}

$(function () {
    main.defineReponsiveNavToggle();
    main.defineDefaultLoader();
    new Home();
});

class Home {
    constructor() {
        this.modal = $('.modal');
        this.modalContent = $('.modal-content');
        this.modalClose = $('.modal-close');
        this.defineFiltroSalasBind();
        this.defineDetalheAgendamentosBind();
        this.defineModalBinds();
    }

    defineFiltroSalasBind() {
        let self = this;
        this.filtroButton = $('#but-filtro');
        this.filtroButton.click(function () {
            $.get('/home/salas')
                .then(res => {
                    self.modal.css("display", "flex");
                    self.modalContent.html(res);
                    new FiltroSalas();
                })
                .fail(err => {
                    self.modal.toggle();
                    console.log(err);
                });
        });
    }

    defineDetalheAgendamentosBind() {
        let self = this;
        $('.is-agendamento').click(function () {
            let agendamento = $(this);
            let id = agendamento.find('.is-id');
            $.get(`agendamento/detalhes/${id}`)
                .then(res => {
                    self.modal.css("display", "flex");
                    self.modalContent.html(res);
                })
                .fail(err => {
                    self.modal.toggle();
                    console.log(err);
                });
        });
    }

    defineModalBinds() {
        let self = this;
        this.modalClose.click(function () {
            self.modal.toggle();
        });
    }
}