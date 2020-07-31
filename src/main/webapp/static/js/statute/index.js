let vm = new Vue({
    el: '.page-content',
    data: {
        pageInfo: {
            pageNum: 1,
            pageSize: 5
        },
        type: '',
        statute:{},
        active:true,
        ueditorConfig:{
            UEDITOR_HOME_URL:'static/ueditor/',
            serverUrl:'doUeditor',
            maximumWords:100000
        }
    },
    methods: {
        selectPage: function () {
            axios({
                url: `manager/statute/selectPage/${this.pageInfo.pageNum}/${this.pageInfo.pageSize}`,
                params: {
                    type: this.type
                }
            }).then((response) => {
                this.pageInfo = response.data.obj;
            }).catch(error => {
                layer.msg(error.message);
            })
        },
        _selectPage: function (pageNum, pageSize) {
            this.pageInfo.pageNum = pageNum;
            this.pageInfo.pageSize = pageSize;
            this.selectPage();
        },
        selectAll: function () {
            this.type = '';
            this._selectPage(1, this.pageInfo.pageSize);
        },
        toUpdate: function (statute) {
            layer.obj = statute;
            layer.open({
                type: 2,
                title: false,
                area: ['60%', '80%'],
                content: 'manager/statute/toUpdate',
                end: () => {
                    if (layer.success == undefined || !layer.success) {
                        this.selectPage();
                    }
                }
            })
        },
        toDelete: function (id) {
            layer.msg('确定要删除吗？', {
                time: 0, //不自动关闭
                btn: ['是', '否'],
                yes: (index)=> {
                    let app = {
                        id: id,
                        delFlag: 1
                    }
                    axios({
                        url: 'manager/statute/doUpdate',
                        data: app,
                        method: 'put'
                    }).then((response) => {
                        if (response.data.success) {
                            layer.close(index);
                            layer.msg(response.data.msg);
                            this.selectPage();
                        }else {
                            layer.msg(response.data.msg)
                        }
                    }).catch(error=>{
                        layer.msg(error.message)
                    })
                }
            });
        },
        changeActive:function(){
            this.active=!this.active;
        },
        toInsert:function () {
            axios({
                url:'manager/statute/toInsert',
                method:'post',
                data:this.statute
            }).then((response)=>{
                if (response.data.success){
                    this.changeActive();
                    this.statute={};
                    this.selectPage();
                }
            })
        }
    },
    created: function () {
        this.selectPage();
    },
    mounted:function () {
        jeDate({
            dateCell:'#indate',
            format:'YYYY-MM-DD',
            zIndex:9999,
            choosefun: (val)=> {
                this.statute.pubDate=val;
            }
        })
    },
    components:{
        VueUeditorWrap
    }
});