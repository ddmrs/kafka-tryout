var app = new Vue({
    el: '#app',
    data: {
        total: 0.00,
        donations: []
    },
    methods: {
        giveMoney: function (e) {
            e.preventDefault();
            var bla = this;
            axios.post('http://localhost:8080/donate', {})
                .then(function (resp) {
                    console.log("Donatie van " + resp.data.name + " voor € " + resp.data.amount);
                });
        },
        getDonations: function () {
            var bla = this;
            axios.get('http://localhost:8080/listAll')
                .then(function (resp) {
                    bla.donations = resp.data.reverse();
                    var total = 0;
                    for (var i = 0; i < bla.donations.length; i++) {
                        total += bla.donations[i].amount;
                    }
                    bla.total = total;
                });
        },
        loopy: function () {
            var bla = this;
            setTimeout(function () {
                bla.getDonations();
                bla.loopy();
            }, 5000);
        }
    },
    created: function () {
        this.loopy();
        // this.getConsumptions();
    }
});