let vm = new Vue({
    el: '.page-content',
    data: function () {
        return {
            pageInfo: {
                pageNum: 1,
                pageSize: 5
            },
            name: '',
            pId: '',
            nodes: [],
            setting: {  //树配置对象，用于设置树的结构的全局配置
                data: {
                    simpleData: { //简单数据格式设置
                        enable: true,  //开启简单数据格式，自动将一维数组组装成父子结构
                        pIdKey: 'parentId'  //默认的父id属性名为pId
                    }
                },
                //回调函数设置，用于设置一些事件回调函数
                callback: {
                    onClick: this.onClick,
                    beforeEditName: this.beforeEditName,
                    beforeRemove: this.beforeRemove
                },
                view: {
                    //用于当鼠标移动到节点上时，显示用户自定义控件
                    addHoverDom: this.addHoverDom,
                    //用于当鼠标移出到节点上时，隐藏用户自定义控件
                    removeHoverDom: this.removeHoverDom
                },
                edit: {
                    enable: true,
                    renameTitle: '修改',
                    removeTitle: '删除'
                }
            },
        }
    },
    methods: {
        selectPage: function () {
            if (this.pId != '') { //根据pid查询所有子区域
                axios({
                    url: `manager/area/selectById/${this.pageInfo.pageNum}/${this.pageInfo.pageSize}`,
                    params: {
                        id: this.pId,
                    }
                }).then((response) => {
                    this.pageInfo = response.data.obj;
                }).catch(error => {
                    layer.msg(error.message)
                })
            } else {
                axios({
                    url: `manager/area/selectByName/${this.pageInfo.pageNum}/${this.pageInfo.pageSize}`,
                    params: {
                        name: this.name,
                    }
                }).then((response) => {
                    this.pageInfo = response.data.obj;
                }).catch(error => {
                    layer.msg(error.message)
                })
            }
        },
        _selectPage: function (pageNum, pageSize) {
            this.pageInfo.pageNum = pageNum;
            this.pageInfo.pageSize = pageSize;
            this.selectPage();
        },
        selectAll: function () {
            this.name = '';
            this.pId = '';
            this.selectPage();
        },
        toUpdate: function (obj) {
            layer.obj = obj;
            layer.open({
                type: 2,
                title: false,
                area: ['80%', '90%'],
                content: 'manager/area/toUpdate',
                end: () => {
                    if(layer.success==undefined||!layer.success){//直接关闭子窗口未更新或更新失败
                        this.selectPage();
                    }
                },
            });
        },
        toDelete:function(id){
            layer.msg('确定要删除吗？', {
                time: 0, //不自动关闭
                btn: ['是', '否'],
                yes: (index) => {
                    let area = {
                        id: id,
                        delFlag: 1
                    }
                    axios({
                        url:'manager/area/toDelete',
                        method: 'put',
                        data:area,
                    }).then((response)=>{
                        if (response.data.success){
                            layer.close(index);
                            layer.msg(response.data.msg);
                            this.selectPage();
                            this.initTree();
                        }
                    }).catch((error)=>{
                        layer.msg(error.message)
                    })
                },

            })
        },
        download:function(){
            //文件下载请求
            location.href="manager/area/download";
        },
        upload:function(event){
            /**
             * 1.创建表单对象FormData
             2.获取点击的事件源中的文件对象
             3.表单对象添加文件对象file
             4.设置提交请求content-type为multipart/form-data  method必须为post
             5.发送ajax请求
             * @type {FormData}
             */
            let formData = new FormData;
            //file参数名（必须与后台接口参数名一致） 值是文件对象
            // console.log(event.target.files[0]);
            formData.append("file",event.target.files[0]);
            axios({
                url:"manager/area/upload",
                method:'post',
                data:formData,
                //设置请求头的请求体类型为文件上传格式
                headers:{'Content-Type':'multipart/form-data'}
            }).then((response)=>{
                layer.msg(response.data.msg);
            }).catch((error)=>{
                layer.msg(error.message);
            })
        },
        initTree: function () {
            axios({
                url: 'manager/area/select'
            }).then((response) => {
                this.nodes = response.data.obj;
                //动态添加一个父节点
                this.nodes[this.nodes.length] = {id: 0, name: '区域管理', open: true}
                /*
                init():初始化树的api
                obj:需要挂载树的jq节点对象
                setting：树的配置对象
                nodes： 树的显示节点信息数组
                */
                // let zTreeObj = $.fn.zTree.init(obj,setting,nodes);
                let zTreeObj = $.fn.zTree.init($('#treeMenu'), this.setting, this.nodes);
            }).catch(error => {
                layer.msg(error.message);
            })
        },
        onClick: function (event, treeId, treeNode) {//点击事件触发的函数
            /*console.log(event);//事件对象
            console.log(treeId);//树节点id
            console.log(treeNode);//当前点击的节点对象*/

            this.pId = treeNode.id;
            this.selectPage();

        },
        beforeEditName: function (treeId, treeNode) {
            //阻止默认编辑节点状态,弹出更新窗口
            layer.open({
                type: 2,
                title: false,
                area: ['80%', '90%'],
                content: 'manager/area/toUpdate'
            });
            return false;
        },
        beforeRemove: function (treeId, treeNode) {
            //根据返回值false则阻止删除节点  true则会将节点删除
            return false;
        },
        addHoverDom: function (treeId, treeNode) {
            //获取经过节点的id
            let tId=treeNode.tId;
            //1.获取span节点
            let aObj = $(`#${tId}_span`);
            //2.获取增加按键的id
            let spanObj = $(`#${tId}_add`);
            //判断是否存在，已存在，阻止创建
            if(spanObj.length>0){return};
            //3.获取tId值，组装  新增的span标签
            let span = `<span class="button add" id="${tId}_add" title="添加"  ></span>`
            //4.插入到显示节点名的dom后面
            aObj.after(span);
            //5.获取新增节点，绑定点击事件
            $(`#${tId}_add`).on('click',function () {
                // $(`#${treeNode.tId}_add`).unbind().remove();
                layer.open({
                    type: 2,        //加载一个页面
                    title: false,
                    area: ['80%', '90%'],//设置宽高   px  或比例  占据父窗口
                    content: 'manager/area/toAdd',
                    /*end:()=>{ //关闭子窗口后的回调函数  会把this替换掉

                    }*/
                })
            });

            // console.log(treeNode);
        },
        removeHoverDom: function (treeId, treeNode) {

            //解除节点的绑定事件和删除节点
            $(`#${treeNode.tId}_add`).unbind().remove();

            // console.log(treeNode);
        }
    },
    created: function () {
        this.selectPage();
    },
    mounted: function () {
        this.initTree();
    }
});