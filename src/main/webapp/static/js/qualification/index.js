let vm = new Vue({
    el: '.page-content',
    data: {
        pageInfo: {
            pageNum: 1,
            pageSize: 5
        },
        conditional: {
            type: "",
            check: ""
        },
    },
    methods: {
        selectPage: function () {
            axios({
                url: `manager/qualification/selectPage/${this.pageInfo.pageNum}/${this.pageInfo.pageSize}`,
                data: this.conditional,
                method: 'post'
            }).then((response) => {
                this.pageInfo = response.data.obj;
            }).catch((error) => {
                layer.msg(error.message);
            });
        },
        _selectPage: function (pageNum, pageSize) {
            this.pageInfo.pageNum = pageNum;
            this.pageInfo.pageSize = pageSize;
            this.selectPage();
        },
        selectAll: function () {
            //查询所有
            this.conditional = {
                //将条件清空
                type: "",
                check: ""
            }
            this._selectPage(1, this.pageInfo.pageSize);
        },
        toUpdate: function (qualification) {
            axios({
                url: `manager/qualification/getPath/${qualification.uploadUserId}`,
            }).then((response)=>{
                qualification.address=response.data.obj+'/'+qualification.address;
                layer.obj = qualification;

                layer.open({
                    type: 2,
                    area: ['700px', '450px'],
                    fixed: false, //不固定
                    content: 'manager/qualification/toUpdate',
                    end:()=>{
                        //关闭子窗口，父窗口回调，刷新整个页面
                        this.selectPage();
                    }
                })
            })
        }
    },
    created: function () {
        this.selectPage();
    }
});