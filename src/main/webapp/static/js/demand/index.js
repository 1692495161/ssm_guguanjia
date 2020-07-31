let vm = new Vue({
    el: ".page-content",
    data: {
        pageInfo: {
            pageNum: 1,
            pageSize: 5
        }
    },
    methods: {
        selectPage: function () {
            axios({
                url: `manager/demand/selectPage/${this.pageInfo.pageNum}/${this.pageInfo.pageSize}`
            }).then((response) => {
                console.log(response.data.obj)
                this.pageInfo = response.data.obj;
            })
        },
        _selectPage: function (pageNum, pageSize) {
            this.pageInfo.pageNum = pageNum;
            this.pageInfo.pageSize = pageSize;
            this.selectPage();
        },
        showDemand: function (demand) {
            layer.obj = demand;

            layer.open({
                type: 2,
                area: ['700px', '450px'],
                fixed: false, //不固定
                content: 'manager/demand/detail',
                /*end: () => {
                    this.selectPage();
                }*/
            });
        }
    },
    created: function () {
        this.selectPage();
    }
});