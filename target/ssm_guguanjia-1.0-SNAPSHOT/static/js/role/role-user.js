let vm=new Vue({
    el:'.page-content',
    data:function () {
        return{
            role:{},
            yxUsers:[],//已分配的用户
            dxUsers:[],//待选的用户
            nodes: [],
            oid:'',
            showDelete:false, //移除人员按钮样式  true:显示   false：隐藏
            showInsert:false, //添加人员按钮样式  true:显示   false：隐藏
            yxIds:[], //已选人员框中勾选的需要移除的人员id
            dxIds:[], //待选人员框中勾选的需要添加的人员id
            setting: {
                data: {
                    simpleData: {
                        enable: true,
                        pIdKey: 'parentId'
                    }
                },
                callback: {
                    onClick: this.onClick,
                },
                view: {//显示回调，当节点显示的时候，会触发回调
                    fontCss: this.fontCss
                }
            }
        }
    },
    methods:{
        checkYxUsers:function(id){
            for (let i in this.yxUsers){
                //点击组件，判断在已选用户中是否存在，修改其show的布尔值
                if (this.yxUsers[i].id===id){
                    this.yxUsers[i].show=!this.yxUsers[i].show;
                    if (this.yxUsers[i].show){
                        //如果有选中的，则显示移除按键
                        this.showDelete=true;
                        //并将已分配的用户id加入yxIds中
                        this.yxIds.push(this.yxUsers[i].id);
                    }else {
                        //如果是取消勾选，将当前用户id从yxIds中移除
                        for (let j in this.yxIds) {
                            if (this.yxIds[j]==id){
                                //splice(index,num)  从开始index索引位置删除指定num个数元素
                                this.yxIds.splice(j,1);
                            }
                        }
                    }
                }
            }
            //判断是否有被选中的选项，否则隐藏按钮
            if (this.yxIds.length<=0){
                this.showDelete=false;
            }
        },
        selectRole:function () {//选择已分配角色人员
            //根据角色id查询当前角色已授权人员
            axios({
                url:`manager/sysuser/selectByRid`,
                params:{
                    rid:this.role.id
                }
            }).then((response)=>{
                this.yxUsers = response.data.obj;  //获取当前角色已分配的用户列表
                for (let i in this.yxUsers) {
                    this.yxUsers[i].show=false;//动态生成标记变量用于checkbox不选中
                }
            }).catch(error=>{
                console.log(error);
            })
        },
        deleteBatch:function(){
            axios({
                url:'manager/role/deleteBatch',
                params: {
                    //数组对象序列化成字符串，放入地址栏的时候会自动添加key[]  ids->ids[]
                    //this.dxIds+''->变成字符串，序列化处理的时候js引擎会将数组处理成 ids='2,26' 放入到地址栏
                    rid:this.role.id,
                    ids:this.yxIds+'',
                }
            }).then((response)=>{
                layer.msg(response.data.msg);
                if (response.data.success){
                    //刷新待选人员
                    this.selectNoRole();
                    this.showInsert=false;
                    this.dxIds=[];
                    //刷新已选人员
                    this.selectRole();
                    this.showDelete=false;
                    this.yxIds=[];
                }
            }).catch(error=>{
                layer.msg(error.message)
            })
        },
        selectNoRole:function(){
            axios({
                url: 'manager/sysuser/selectNoRole',
                params:{
                    oid:this.oid,
                    rid:this.role.id,
                }
            }).then(response=>{
                this.dxUsers=response.data.obj;
                //添加checkbox选中属性
                for (let i in this.dxUsers){
                    this.dxUsers[i].show=false;
                }
            }).catch(error=>{
                layer.msg(error.message);
            })
        },
        checkDxUsers:function(id){
            for (let i in this.dxUsers){
                if (this.dxUsers[i].id===id){
                    this.dxUsers[i].show=!this.dxUsers[i].show;
                    if (this.dxUsers[i].show){
                        this.showInsert=true;
                        //将所有勾选的待选人员的id加入dxIds
                        this.dxIds.push(this.dxUsers[i].id)
                    }else {//未勾选将当前用户id从dxIds中移除
                        //splice(index,num)  从开始index索引位置删除指定num个数元素
                        for (let j in this.dxIds){
                            if (this.dxIds[j]==id){
                                this.dxIds.splice(j,1);
                            }
                        }
                    }
                }
            }
            if (this.dxIds.length<=0){
                this.showInsert=false;
            }
        },
        insertBatch:function(){
            axios({
                url:'manager/role/insertBatch',
                params: {
                    //数组对象序列化成字符串，放入地址栏的时候会自动添加key[]  ids->ids[]
                    //this.dxIds+''->变成字符串，序列化处理的时候js引擎会将数组处理成 ids='2,26' 放入到地址栏
                    rid:this.role.id,
                    cids:this.dxIds+'',
                }
            }).then(response=>{
                layer.msg(response.data.msg);
                if (response.data.success){
                    //刷新待选人员
                    this.selectNoRole();
                    this.showInsert=false;
                    this.dxIds=[];
                    //刷新已选人员
                    this.selectRole();
                    this.showDelete=false;
                    this.yxIds=[];
                }
            }).catch(error=>{
                layer.msg(error.message)
            })
        },
        initTree: function () {
            axios({
                url: 'manager/office/select'
            }).then(response => {
                this.nodes = response.data.obj;
                /*init(obj,setting,nodes)初始化树的api
                   * obj是需要挂载树的jq节点对象
                   * setting是树配置对象
                   * nodes：树的显示节点信息数组
                   * */
                //必须保证响应返回对nodes赋值后，再初始化树
                let zTreeObj = $.fn.zTree.init($('#treeOffice'), this.setting, this.nodes);
            }).catch(error => {
                layer.msg(error.message);
            });

        },
        onClick: function (event, treeId, treeNode) {//点击事件触发的函数
            console.log(event);//事件对象
            console.log(treeId);//树id ：  treeOffice
            console.log(treeNode);//当前点击的节点对象

            let zTreeObj = $.fn.zTree.getZTreeObj("treeOffice");  //根据树id获取树对象
            //获取所有树节点，多维结构
            let nodes = zTreeObj.getNodes();
            //转换成一维结构
            nodes = zTreeObj.transformToArray(nodes);

            //还原所有高亮属性为false
            for(let i in nodes){
                nodes[i].highLight=false;
                zTreeObj.updateNode(nodes[i]);
            }
            treeNode.highLight=true;//设置高亮
            zTreeObj.updateNode(treeNode);//更新节点

            this.oid=treeNode.id;
            this.selectNoRole();

        },
        fontCss: function (treeId, treeNode) {
            //返回json格式   对字体进行设置样式
            // return treeNode.id===106?{color:'red'}:{color:'black'}
            //高亮属性的节点显示红色
            return treeNode.highLight ? {color: 'red'} : {color: 'black'}
        }
    },
    created:function(){
        this.role.id=parent.layer.roleId;
        this.role.name = parent.layer.roleName;
        this.yxUsers=parent.layer.users;
    },
    mounted:function () {
        this.initTree();
    }
});