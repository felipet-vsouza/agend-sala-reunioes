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
        this.reunioes = $('#pn-reunioes');
        this.defineFiltroSalasBind();
        this.defineAgendamentoSalasBind();
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
                    console.error(err);
                });
        });
    }

    defineDetalheAgendamentosBind() {
        let self = this;
        $('.is-agendamento').click(function () {
            let agendamento = $(this);
            let id = agendamento.find('.is-id').val();
            $.get(`/agendamento/detalhes/${id}`)
                .then(res => {
                    self.modal.css("display", "flex");
                    self.defineModalContent(res);
                    $('#agend-cancela').click(function () {
                        new PopupConfirmation({
                            onAgreed: function () {
                                $.post(`/agendamento/cancelar/${id}`)
                                    .then(res => {
                                        self.defineModalContent(res);
                                        self.refreshPainelReunioes()
                                    })
                                    .fail(err => {
                                        console.error('Erro na requisição: ', err);
                                    });
                            },
                            onDisagreed: function () {
                                self.modal.toggle();
                            },
                            container: self
                        });
                    });
                })
                .fail(err => {
                    console.error(err);
                });
        });
    }

    defineAgendamentoSalasBind() {
        let self = this;
        this.filtroButton = $('#but-agendamento');
        this.filtroButton.click(function () {
            $.get('/home/agendamento')
                .then(res => {
                    self.modal.css("display", "flex");
                    self.modalContent.html(res);
                    new AgendamentoSalas();
                })
                .fail(err => {
                    self.modal.css("display", "flex");
                    self.modalContent.html(err);
                });
        });
    }

    defineModalBinds() {
        let self = this;
        this.modalClose.click(function () {
            self.modal.toggle();
        });
    }

    defineModalContent(content) {
        this.modalContent.html(content);
    }

    refreshPainelReunioes() {
        let self = this;
        $.get('/home/reunioes')
            .then(res => {
                self.reunioes.html(res);
            })
            .fail(err => {
                console.error('Erro na requisição: ', err);
            });
    }
}

class PopupConfirmation {
    constructor(configurations) {
        this.onAgreed = configurations.onAgreed;
        this.onDisagreed = configurations.onDisagreed;
        this.container = configurations.container;
        this.showPopupAndSetBinds();
    }

    showPopupAndSetBinds() {
        let self = this;
        $.get('/home/confirmation')
            .then(res => {
                self.container.defineModalContent(res);
                $('#conf-sim').click(self.onAgreed);
                $('#conf-nao').click(self.onDisagreed);
            })
            .fail(err => {
                console.error('Erro na requisição: ', err);
            });
    }
}