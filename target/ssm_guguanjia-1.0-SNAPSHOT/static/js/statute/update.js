let vm = new Vue({
    el: '.page-content',
    data: {
        statute: {},
        success: false,
        ueditorConfig:{
            //前端默认ueditor资源文件加载路径
            //VueUeditorWrap会从默认加载路径中加载ueditor.all.min.js和config.js等资源
            UEDITOR_HOME_URL:'static/ueditor/',
            serverUrl: 'doUeditor',
            maximumWords:100000
        }
    },
    methods: {
        doUpdate: function () {
            axios({
                url: 'manager/statute/doUpdate',
                method: 'put',
                data: this.statute,
            }).then((response) => {
                this.success = response.data.success;
                let index = parent.layer.getFrameIndex(window.name);
                if (this.success) {
                    parent.layer.close(index);
                    parent.layer.msg(response.data.msg);
                    parent.layer.success = response.data.success;
                } else {
                    layer.msg(response.data.msg)
                }
            })
        },
        doClose: function () {
            if (this.success == undefined || !this.success) {
                let index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            }
        },
    },
    created: function () {
        this.statute = parent.layer.obj;
    },
    mounted: function () {
        jeDate({
            dateCell: '#modifydate',  //设置时间插件的dom节点
            format: 'YYYY-MM-DD',
            zIndex: 9999,
            choosefun: (val) => { //选中日期后回调
                //动态获取jeDate赋值后的日期，给vue的statute对象的pubDate赋值
                this.statute.pubDate = val;
            }

        })
    },
    components:{
        VueUeditorWrap
    }
});