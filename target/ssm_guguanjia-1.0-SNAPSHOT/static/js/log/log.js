let vm =new Vue({
    el:'.page-content',
    data:{
        pageInfo:{
          pageNum:1,
          pageSize:5
        },
        log:{
            description:'',
            type:''
        },
    },
    methods:{
        selectPage:function () {
            axios({
                url:`manager/syslog/selectPage/${this.pageInfo.pageNum}/${this.pageInfo.pageSize}`,
                method:'post',
                data:this.log
            }).then(response=>{
                this.pageInfo=response.data.obj;
            }).catch(error=>{
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
                description:'',
                type:''
            }
            this.selectPage();
        },
    },
    created:function () {
        this.selectPage();
    }
});