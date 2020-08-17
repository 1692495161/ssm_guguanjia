let vm = new Vue({
    el: '.page-content',
    data: {
        pageInfo: {
            pageNum: 1,
            pageSize: 5
        },
        log: {
            description: '',
            type: ''
        },
    },
    methods: {
        selectPage: function () {
            axios({
                url: `manager/syslog/selectPage/${this.pageInfo.pageNum}/${this.pageInfo.pageSize}`,
                method: 'post',
                data: this.log
            }).then(response => {
                this.pageInfo = response.data.obj;
            }).catch(error => {
                layer.msg(error.message)
            })
        },
        _selectPage: function (pageNum, pageSize) {
            this.pageInfo.pageNum = pageNum;
            this.pageInfo.pageSize = pageSize;
            this.selectPage();
        },
        selectAll: function () {
            this.pageInfo = {
                //初始化,第一次页面加载的时候使用
                pageNum: 1,
                pageSize: 5
            };
            this.log = {
                description: '',
                type: ''
            }
            this.selectPage();
        },
        toDetail: function (logs) {
            layer.obj = logs;
            layer.open({
                type: 2,
                title: false,
                area: ['90%', '90%'],
                content: 'manager/syslog/toDetail',
            })
        },
        toDelete: function (id) {
            layer.msg('确定删除？', {
                time: 0 //不自动关闭
                , btn: ['是', '否']
                , yes: (index)=> {
                    axios({
                        url: 'manager/syslog/toDelete',
                        params: {id: id},
                    }).then(response => {
                        if (response.data.success) {
                            layer.msg(response.data.msg)
                            layer.close(index);
                            this.selectPage();
                        } else {
                            layer.msg(response.data.msg)
                        }
                    }).catch(error => {
                        layer.msg(error.message)
                    })
                }
            });
        }
    },
    created: function () {
        this.selectPage();
    }
});