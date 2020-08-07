let vm = new Vue({
        el: '.page-content',
        data: function () {
            return {
                setting: {
                    data: {
                        simpleData: {
                            enable: true,
                            pIdKey: 'parentId'
                        }
                    },
                    check: {
                        enable: true,
                        chkboxType: {'Y': 'p', 'N': 'ps'}  //Y控制选中   N控制取消选中   p:父项关联  s子项关联
                    }
                },
                nodes: [],//所有权限的树对象
                resources: [],//当前点击角色的已有权限
                treeObj: {},//勾选已有权限的树对象
                offices: [],//所有的机构的树对象
                officeNodes: [],//勾选已分配机构
                officeObj: '',//勾选的已分配的机构树对象
                role: {
                    offices: null,  //新机构
                    oldOffices: null, //旧授权机构
                    resources: null, //新权限
                    oldResources: null  //旧授权权限
                }
            }
        },
        methods: {
            doUpdate: function () {
                //将已有的值赋给旧节点数组
                this.role.oldResources = this.resources;
                this.role.oldOffices = this.officeNodes;
                //获取新的被勾选的权限
                this.role.resources = this.treeObj.getCheckedNodes(true);
                if (this.role.offices.length > 0) {
                    //如果有根节点，则去掉
                    if (this.role.resources != undefined && this.role.resources[0].id == 0) {
                        this.role.resources.splice(0, 1);
                    }
                }
                if (this.role.dataScope == '9') {
                    this.role.offices = this.officeObj.getCheckedNodes(true);
                    if (this.role.offices.length > 0) {
                        if (this.role.offices != undefined && this.role.offices[0].id == 0) {
                            this.role.offices.splice(0, 1);
                        }
                    }
                }
                axios({
                    url: 'manager/role/doUpdate',
                    data: this.role,
                    method: 'put'
                }).then(response => {
                    let index = parent.layer.getFrameIndex(window.name);
                    parent.layer.success = response.data.success;
                    if (response.data.success) {//成功更新，关闭当前子窗口，并且通过父窗口提示
                        parent.layer.close(index);
                        parent.layer.msg(response.data.msg);

                    } else {
                        layer.msg(response.data.msg);//子窗口中提示
                    }
                }).catch(error => {
                    layer.msg(error.message);
                })
            },
            initTree:

                function () {
                    axios({
                        url: 'manager/menu/select',
                    }).then(resopnse => {
                        this.nodes = resopnse.data.obj;
                        this.nodes[this.nodes.length] = {
                            'id': '0',
                            'name': '全部权限'
                        }
                        //根据当前树的全部节点中查找到需要设置选中的节点，选中处理
                        this.selectByRid();
                    }).catch(error => {
                        layer.msg(error.message)
                    })
                }

            ,
            selectByRid: function () {
                axios({
                    url: 'manager/menu/selectByRid',
                    params: {
                        rid: this.role.id
                    }
                }).then(response => {
                    this.resources = response.data.obj;
                    for (let i in this.nodes) {
                        for (let j in this.resources) {
                            if (this.nodes[i].id === this.resources[j].id) {
                                //在所有权限中找到已有的权限，设置其checked为true，勾中
                                this.nodes[i].checked = true;
                                break;
                            }
                        }
                    }
                    this.treeObj = $.fn.zTree.init($('#select-treetreeSelectResEdit'), this.setting, this.nodes);
                }).catch(error => {
                    layer.msg(error.message)
                })
            }
            ,
            initOfficeTree: function () {
                axios({
                    url: 'manager/office/select',
                }).then(response => {
                    this.offices = response.data.obj;
                    this.offices[this.offices.length] = {
                        'id': '0',
                        'name': '全部机构'
                    }
                    //根据当前树的全部节点中查找到需要设置选中的节点，选中处理
                    this.selectOfficeByRid();
                }).catch(error => {
                    layer.msg(error.message)
                })
            }
            ,
            selectOfficeByRid: function () {
                axios({
                    url: 'manager/office/selectOfficeByRid',
                    params: {
                        rid: this.role.id
                    }
                }).then(response => {
                    this.officeNodes = response.data.obj;
                    for (let i in this.offices) {
                        for (let j in this.officeNodes) {
                            if (this.offices[i].id === this.officeNodes[j].id) {
                                //在所有权限中找到已分配的权限，设置其checked为true，勾中
                                this.offices[i].checked = true;
                                break;
                            }
                        }
                    }
                    this.officeObj = $.fn.zTree.init($('#select-treetreeSelectOfficeEdit'), this.setting, this.offices);
                    $("#treeSelectOfficeEdit").css('display', 'inline-block')
                }).catch(error => {
                    layer.msg(error.message)
                })
            }
            ,
            changeDataScope: function () {
                if (this.role.dataScope == '9') {
                    //本身就是9
                    if (this.officeObj != undefined && this.officeObj != '') {
                        $("#treeSelectOfficeEdit").css('display', 'inline-block')
                    } else {
                        //从其他变为9
                        this.initOfficeTree();
                        $("#treeSelectOfficeEdit").css('display', 'inline-block')
                    }
                } else {
                    //不等于9的话，如果已创建就隐藏
                    if (this.officeObj != undefined && this.officeObj != '') {
                        $("#treeSelectOfficeEdit").css('display', 'none')
                    }
                }
            },
            toSelect:function () {
                layer.open({
                    type:2,
                    title:false,
                    area:['100%', '100%'],
                    content:'manager/role/toSelect',
                    end:()=>{
                        this.role.officeName=layer.parentName;
                    }
                })
            }
        },
        created: function () {
            this.role = parent.layer.obj;
        }
        ,
        mounted: function () {
            this.initTree();
            if (this.role.dataScope === '9') {
                this.initOfficeTree();
            }
        }
    })
;