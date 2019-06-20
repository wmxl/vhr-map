<template>
  <div>
    <el-container>
      <el-header style="padding: 0px;display:flex;justify-content:space-between;align-items: center">
        <div style="display: inline">
          <el-input
            placeholder="Remember to press enter..."
            clearable
            @change="keywordsChange"
            style="width: 300px;margin: 0px;padding: 0px;"
            size="mini"
            :disabled="advanceSearchViewVisible"
            @keyup.enter.native="searchEmp"
            prefix-icon="el-icon-search"
            v-model="keywords">
          </el-input>
          <el-button type="primary" size="mini" style="margin-left: 5px" icon="el-icon-search" @click="searchEmp">搜索
          </el-button>
          <el-button slot="reference" type="primary" size="mini" style="margin-left: 5px"
                     @click="showAdvanceSearchView"><i
            class="fa fa-lg" v-bind:class="[advanceSearchViewVisible ? faangledoubleup:faangledoubledown]"
            style="margin-right: 5px"></i>高级搜索
          </el-button>
        </div>

        <div style="margin-left: 5px;margin-right: 20px;display: inline">
          <el-upload
            :show-file-list="false"
            accept="application/vnd.ms-excel"
            action="/employee/basic/importEmp"
            :on-success="fileUploadSuccess"
            :on-error="fileUploadError" :disabled="fileUploadBtnText=='正在导入'"
            :before-upload="beforeFileUpload" style="display: inline">
            <el-button size="mini" type="success" :loading="fileUploadBtnText=='正在导入'"><i class="fa fa-lg fa-level-up"
                                                                                          style="margin-right: 5px"></i>{{fileUploadBtnText}}
            </el-button>
          </el-upload>
          <el-button type="success" size="mini" @click="exportEmps"><i class="fa fa-lg fa-level-down"
                                                                       style="margin-right: 5px"></i>导出数据
          </el-button>
          <el-button type="primary" size="mini" icon="el-icon-plus"
                     @click="showAddEmpView">
            添加员工
          </el-button>
        </div>

      </el-header>
      <el-main style="padding-left: 0px;padding-top: 0px">
        
        <div>
<!--          主列表之前-->
          <transition name="slide-fade">
            <div
              style="margin-bottom: 10px;border: 1px;border-radius: 5px;border-style: solid;padding: 5px 0px 5px 0px;box-sizing:border-box;border-color: #20a0ff"
              v-show="advanceSearchViewVisible">
              <el-row>
                <el-col :span="4">
                  职位:
                  <el-select v-model="emp.posId" style="width: 130px" size="mini" placeholder="请选择职位">
                    <el-option
                      v-for="item in positions"
                      :key="item.id"
                      :label="item.name"
                      :value="item.id">
                    </el-option>
                  </el-select>
                </el-col>
                <el-col :span="4">
                  职称:
                  <el-select v-model="emp.jobLevelId" style="width: 130px" size="mini" placeholder="请选择职称">
                    <el-option
                      v-for="item in joblevels"
                      :key="item.id"
                      :label="item.name"
                      :value="item.id">
                    </el-option>
                  </el-select>
                </el-col>
              </el-row>
              <el-row style="margin-top: 10px">
                <el-col :span="5">
                  所属部门:
                  <el-popover
                    v-model="showOrHidePop2"
                    placement="right"
                    title="请选择部门"
                    trigger="manual">
                    <el-tree :data="deps" :default-expand-all="true" :props="defaultProps" :expand-on-click-node="false"
                             @node-click="handleNodeClick2"></el-tree>
                    <div slot="reference"
                         style="width: 130px;height: 26px;display: inline-flex;font-size:13px;border: 1px;border-radius: 5px;border-style: solid;padding-left: 13px;box-sizing:border-box;border-color: #dcdfe6;cursor: pointer;align-items: center"
                         @click="showDepTree2" v-bind:style="{color: depTextColor}">{{emp.departmentName}}
                    </div>
                  </el-popover>
                </el-col>

                <el-col :span="5" :offset="4">
                  <el-button size="mini" @click="cancelSearch">取消</el-button>
                  <el-button icon="el-icon-search" type="primary" size="mini" @click="searchEmp">搜索</el-button>
                </el-col>
              </el-row>
            </div>
          </transition>
<!--          主列表之前 结束-->
<!--          主列表-->
          <el-table
            :data="emps"
            v-loading="tableLoading"
            border
            stripe
            @selection-change="handleSelectionChange"
            size="mini"
            style="width: 100%">
            <el-table-column
              type="selection"
              align="left"
              width="30">
            </el-table-column>

            <el-table-column
              prop="name"
              align="left"
              fixed
              label="客户名称"
              width="90">
            </el-table-column>

            <el-table-column
              prop="highSea"
              label="公海"
              width="50">
            </el-table-column>

            <el-table-column
              prop="workID"
              width="85"
              align="left"
              label="客户编码">
            </el-table-column>

            <el-table-column
              prop="province"
              label="省份"
              align="left"
              width="50">
            </el-table-column>
            <el-table-column
              prop="city"
              label="城市"
              align="left"
              width="50">
            </el-table-column>
            <el-table-column
              prop="county"
              label="县"
              align="left"
              width="50">
            </el-table-column>

          
            <el-table-column
              prop="website"
              label="网址"
              align="left"
              width="50">
            </el-table-column>

            <el-table-column
              prop="remark"
              label="备注"
              align="left"
              width="50">
            </el-table-column>

            <el-table-column
              prop="businessReceipt"
              label="营业收入"
              align="left"
              width="50">
            </el-table-column>

            <el-table-column
              prop="businessScope"
              label="营业范围"
              align="left"
              width="50">
            </el-table-column>

            <el-table-column
              prop="propertyValue"
              label="资产总计"
              align="left"
              width="50">
            </el-table-column>

            <el-table-column
              prop="employeeNum"
              label="从业总人数"
              align="left"
              width="50">
            </el-table-column>

            <el-table-column
              prop="products"
              label="主要产品"
              align="left"
              width="50">
            </el-table-column>

            <el-table-column
              prop="propertyType"
              label="资产类型"
              align="left"
              width="50">
            </el-table-column>



            <!-- <el-table-column
              width="85"
              align="left"
              label="出生日期">
              <template slot-scope="scope">{{ scope.row.birthday | formatDate}}</template>
            </el-table-column> -->

<!--            <el-table-column-->
<!--              prop="wedlock"-->
<!--              width="70"-->
<!--              label="婚姻状况">-->
<!--            </el-table-column>-->

            <!-- <el-table-column
              width="50"
              prop="nation.name"
              label="民族">
            </el-table-column> -->
            

            </el-table-column>
            <el-table-column
              prop="email"
              width="180"
              align="left"
              label="电子邮件">
            </el-table-column>
            <el-table-column
              prop="phone"
              width="100"
              label="电话号码">
            </el-table-column>
            <el-table-column
              prop="address"
              width="220"
              align="left"
              label="联系地址">
            </el-table-column>
            <el-table-column
              prop="department.name"
              align="left"
              width="100"
              label="所属部门">
            </el-table-column>
            <el-table-column
              width="100"
              align="left"
              prop="position.name"
              label="职位">
            </el-table-column>
            <el-table-column
              prop="jobLevel.name"
              width="100"
              align="left"
              label="职称">
            </el-table-column>

<!--            <el-table-column-->
<!--              prop="engageForm"-->
<!--              label="聘用形式">-->
<!--            </el-table-column>-->

            <!-- <el-table-column
              width="85"
              align="left"
              label="入职日期">
              <template slot-scope="scope">{{ scope.row.beginDate | formatDate}}</template>
            </el-table-column>
            <el-table-column
              width="85"
              align="left"
              label="转正日期">
              <template slot-scope="scope">{{ scope.row.conversionTime | formatDate}}</template>
            </el-table-column>
            <el-table-column
              width="95"
              align="left"
              label="合同起始日期">
              <template slot-scope="scope">{{ scope.row.beginContract | formatDate}}</template>
            </el-table-column>
            <el-table-column
              width="95"
              align="left"
              label="合同截至日期">
              <template slot-scope="scope">{{ scope.row.endContract | formatDate}}</template> -->
            <!-- </el-table-column> -->
<!--             <el-table-column
              align="left"
              width="70"
              label="合同期限">
              <template slot-scope="scope">{{ scope.row.contractTerm}}年</template>
            </el-table-column> -->
            <el-table-column
              align="left"
              prop="industry"
              label="行业">
            </el-table-column>
            <el-table-column
              fixed="right"
              label="操作"
              width="195">
              <template slot-scope="scope">
                <el-button @click="showEditEmpView(scope.row)" style="padding: 3px 4px 3px 4px;margin: 2px"
                           size="mini">编辑
                </el-button>
                <el-button style="padding: 3px 4px 3px 4px;margin: 2px" type="primary"
                           size="mini">查看高级资料
                </el-button>
                <el-button type="danger" style="padding: 3px 4px 3px 4px;margin: 2px" size="mini"
                           @click="deleteEmp(scope.row)">删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
<!--          主列表结束-->
<!--          主列表之后-->
          <div style="display: flex;justify-content: space-between;margin: 2px">
            <el-button type="danger" size="mini" v-if="emps.length>0" :disabled="multipleSelection.length==0"
                       @click="deleteManyEmps">批量删除
            </el-button>
            <el-pagination
              background
              :page-size="10"
              :current-page="currentPage"
              @current-change="currentChange"
              layout="prev, pager, next"
              :total="totalCount">
            </el-pagination>
          </div>
<!--          主列表之后 结束-->
        </div>

      </el-main>
    </el-container>
<!--    添加数据-->
    <el-form :model="emp" :rules="rules" ref="addEmpForm" style="margin: 0px;padding: 0px;">
      <div style="text-align: left">
        <el-dialog
          :title="dialogTitle"
          style="padding: 0px;"
          :close-on-click-modal="false"
          :visible.sync="dialogVisible"
          width="77%">

          <el-row>
<!--            第一行-->
            <el-col :span="6">
              <div>
                <el-form-item label="客户名称:" prop="name">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.name" size="mini" style="width: 150px"
                            placeholder="请输入员工姓名"></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="5">
              <div>
                <el-form-item label="公海:" prop="highSea">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.highSea" size="mini" style="width: 150px"
                            placeholder="请输入公海"></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="5">
              <div>
                <el-form-item label="省份:" prop="province">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.province" size="mini" style="width: 150px"
                            placeholder="请输入省份"></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="5">
              <div>
                <el-form-item label="城市:" prop="city">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.city" size="mini" style="width: 150px"
                            placeholder="请输入城市"></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="5">
              <div>
                <el-form-item label="县:" prop="county">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.county" size="mini" style="width: 150px"
                            placeholder="请输入县"></el-input>
                </el-form-item>
              </div>
            </el-col>


            <el-col :span="5">
              <div>
                <el-form-item label="网址:" prop="website">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.website" size="mini" style="width: 150px"
                            placeholder="请输入industry"></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="5">
              <div>
                <el-form-item label="备注:" prop="remark">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.remark" size="mini" style="width: 150px"
                            placeholder="请输入industry"></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="5">
              <div>
                <el-form-item label="营业收入:" prop="businessReceipt">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.businessReceipt" size="mini" style="width: 150px"
                            placeholder="请输入industry"></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="5">
              <div>
                <el-form-item label="营业范围:" prop="businessScope">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.businessScope" size="mini" style="width: 150px"
                            placeholder="请输入industry"></el-input>
                </el-form-item>
              </div>
            </el-col>


            <el-col :span="5">
              <div>
                <el-form-item label="资产总计:" prop="propertyValue">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.propertyValue" size="mini" style="width: 150px"
                            placeholder="请输入industry"></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="5">
              <div>
                <el-form-item label="从业总人数:" prop="employeeNum">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.employeeNum" size="mini" style="width: 150px"
                            placeholder="请输入industry"></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="5">
              <div>
                <el-form-item label="主要产品:" prop="products">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.products" size="mini" style="width: 150px"
                            placeholder="请输入industry"></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="5">
              <div>
                <el-form-item label="资产类型:" prop="propertyType">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.propertyType" size="mini" style="width: 150px"
                            placeholder="请输入industry"></el-input>
                </el-form-item>
              </div>
            </el-col>           

          </el-row>
          第二行
          <el-row>
            
            <el-col :span="6">
              <div>
                <el-form-item label="电子邮箱:" prop="email">
                  <el-input prefix-icon="el-icon-message" v-model="emp.email" size="mini" style="width: 150px"
                            placeholder="电子邮箱地址..."></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="7">
              <div>
                <el-form-item label="联系地址:" prop="address">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.address" size="mini" style="width: 200px"
                            placeholder="联系地址..."></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>

          <el-row>
<!--            第三行-->
            <el-col :span="6">
              <div>
                <el-form-item label="职位:" prop="posId">
                  <el-select v-model="emp.posId" style="width: 150px" size="mini" placeholder="请选择职位">
                    <el-option
                      v-for="item in positions"
                      :key="item.id"
                      :label="item.name"
                      :value="item.id">
                    </el-option>
                  </el-select>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="5">
              <div>
                <el-form-item label="职称:" prop="jobLevelId">
                  <el-select v-model="emp.jobLevelId" style="width: 120px" size="mini" placeholder="请选择职称">
                    <el-option
                      v-for="item in joblevels"
                      :key="item.id"
                      :label="item.name"
                      :value="item.id">
                    </el-option>
                  </el-select>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="6">
              <div>
                <el-form-item label="所属部门:" prop="departmentId">
                  <el-popover
                    v-model="showOrHidePop"
                    placement="right"
                    title="请选择部门"
                    trigger="manual">
                    <el-tree :data="deps" :default-expand-all="true" :props="defaultProps" :expand-on-click-node="false"
                             @node-click="handleNodeClick"></el-tree>
                    <div slot="reference"
                         style="width: 150px;height: 26px;display: inline-flex;font-size:13px;border: 1px;border-radius: 5px;border-style: solid;padding-left: 13px;box-sizing:border-box;border-color: #dcdfe6;cursor: pointer;align-items: center"
                         @click.left="showDepTree" v-bind:style="{color: depTextColor}">{{emp.departmentName}}
                    </div>
                  </el-popover>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="7">
              <div>
                <el-form-item label="电话号码:" prop="phone">
                  <el-input prefix-icon="el-icon-phone" v-model="emp.phone" size="mini" style="width: 200px"
                            placeholder="电话号码..."></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
<!--            第四行-->
            <el-col :span="6">
              <div>
                <el-form-item label="工号:" prop="workID">
                  <el-input v-model="emp.workID" disabled size="mini" style="width: 150px"
                            placeholder="员工工号..."></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="5">
              <div>
                <el-form-item label="行业:" prop="industry">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.industry" size="mini" style="width: 150px"
                            placeholder="行业"></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="6">
              <div>
                <el-form-item label="毕业院校:" prop="school">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.school" size="mini" style="width: 150px"
                            placeholder="毕业院校名称"></el-input>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="7">
              <div>
                <el-form-item label="专业名称:" prop="specialty">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.specialty" size="mini" style="width: 200px"
                            placeholder="专业名称"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row>
            第五行
            
          </el-row>
          第六行
          </el-row>
          <span slot="footer" class="dialog-footer">
            <el-button size="mini" @click="cancelEidt">取 消</el-button>
            <el-button size="mini" type="primary" @click="addEmp('addEmpForm')">确 定</el-button>
          </span>
        </el-dialog>
      </div>
    </el-form>
<!--    添加数据结束-->


  </div>
</template>
<!-- JS -->
<script>
  export default {
    data() {
      return {
        emps: [],
        keywords: '',
        fileUploadBtnText: '导入数据',
        faangledoubleup: 'fa-angle-double-up',
        faangledoubledown: 'fa-angle-double-down',
        dialogTitle: '',
        multipleSelection: [],
        depTextColor: '#c0c4cc',
        positions: [],
        joblevels: [],
        totalCount: -1,
        currentPage: 1,
        
        deps: [],
        defaultProps: {
          label: 'name',
          isLeaf: 'leaf',
          children: 'children'
        },
        dialogVisible: false,
        tableLoading: false,
        advanceSearchViewVisible: false,
        showOrHidePop: false,
        showOrHidePop2: false,
        emp: {
          name: '',
          highSea: '',
          province: '',
          city: '',
          county: '',
          website: '',
          remark: '',
          businessReceipt: '',
          businessScope: '',

          propertyValue: '',
          employeeNum: '',
          products: '',
          propertyType: '',
          email: '',
          phone: '',
          address: '',
          departmentId: '',
          departmentName: '所属部门...',
          jobLevelId: '',
          posId: '',

          industry: '',
          specialty: '',
          school: '',
          workState: '',
          workID: '',

          workAge: ''
        },
        rules: {
          name: [{required: true, message: '必填:姓名', trigger: 'blur'}],
          email: [{required: true, message: '必填:电子邮箱', trigger: 'blur'}, {
            type: 'email',
            message: '邮箱格式不正确',
            trigger: 'blur'
          }],
        }
      };
    },
    mounted: function () {
      this.initData();
      this.loadEmps();
    },
    methods: {
      fileUploadSuccess(response, file, fileList){
        if (response) {
          this.$message({type: response.status, message: response.msg});
        }
        this.loadEmps();
        this.fileUploadBtnText = '导入数据';
      },
      fileUploadError(err, file, fileList){
        this.$message({type: 'error', message: "导入失败!"});
        this.fileUploadBtnText = '导入数据';
      },
      beforeFileUpload(file){
        this.fileUploadBtnText = '正在导入';
      },
      exportEmps(){
        window.open("/employee/basic/exportEmp", "_parent");
      },
      cancelSearch(){
        this.advanceSearchViewVisible = false;
        this.emptyEmpData();
        this.loadEmps();
      },
      showAdvanceSearchView(){
        this.advanceSearchViewVisible = !this.advanceSearchViewVisible;
        this.keywords = '';
        if (!this.advanceSearchViewVisible) {
          this.emptyEmpData();
          this.loadEmps();
        }
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      deleteManyEmps(){
        this.$confirm('此操作将删除[' + this.multipleSelection.length + ']条数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          var ids = '';
          for (var i = 0; i < this.multipleSelection.length; i++) {
            ids += this.multipleSelection[i].id + ",";
          }
          this.doDelete(ids);
        }).catch(() => {
        });
      },
      deleteEmp(row){
        this.$confirm('此操作将永久删除[' + row.name + '], 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.doDelete(row.id);
        }).catch(() => {
        });
      },
      doDelete(ids){
        this.tableLoading = true;
        var _this = this;
        this.deleteRequest("/employee/basic/emp/" + ids).then(resp=> {
          _this.tableLoading = false;
          if (resp && resp.status == 200) {
            var data = resp.data;
            
            _this.loadEmps();
          }
        })
      },
      keywordsChange(val){
        if (val == '') {
          this.loadEmps();
        }
      },
      searchEmp(){
        this.loadEmps();
      },
      currentChange(currentChange){
        this.currentPage = currentChange;
        this.loadEmps();
      },
      //加载数据的方法
      loadEmps(){
        var _this = this;
        this.tableLoading = true;
        this.getRequest("/employee/basic/emp?page=" + this.currentPage + "&size=10&keywords=" + this.keywords + "&posId=" + this.emp.posId + "&jobLevelId=" + this.emp.jobLevelId  + "&departmentId=" + this.emp.departmentId + "&beginDateScope=" + this.beginDateScope).then(resp=> {
          this.tableLoading = false;

          if (resp && resp.status == 200) {
            var data = resp.data;
            _this.emps = data.emps;
            _this.totalCount = data.count;

            // console.log("start")
            // console.log(data.emps)
            // console.log(data.emps[0])
            // console.log("end")

//            _this.emptyEmpData();
          }
        })
      },
      addEmp(formName){
        var _this = this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if (this.emp.id) {
              //更新
              console.log("进入更新");
              this.tableLoading = true;
              this.putRequest("/employee/basic/emp", this.emp).then(resp=> {
                console.log("进入更新putRequest");
                console.log(resp)
                console.log(resp.status)

                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  console.log("进入更新if");
                  var data = resp.data;
                  _this.dialogVisible = false;
                  _this.emptyEmpData();
                  _this.loadEmps();
                }
              })
            } else {
              //添加
              this.tableLoading = true;
              this.postRequest("/employee/basic/emp", this.emp).then(resp=> {
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  var data = resp.data;
                  
                  _this.dialogVisible = false;
                  _this.emptyEmpData();
                  _this.loadEmps();
                }
              })
            }
          } else {
            return false;
          }
        });
      },
      cancelEidt(){
        this.dialogVisible = false;
        this.emptyEmpData();
      },
      showDepTree(){
        this.showOrHidePop = !this.showOrHidePop;
      },
      showDepTree2(){
        this.showOrHidePop2 = !this.showOrHidePop2;
      },
      handleNodeClick(data) {
        this.emp.departmentName = data.name;
        this.emp.departmentId = data.id;
        this.showOrHidePop = false;
        this.depTextColor = '#606266';
      },
      handleNodeClick2(data) {
        this.emp.departmentName = data.name;
        this.emp.departmentId = data.id;
        this.showOrHidePop2 = false;
        this.depTextColor = '#606266';
      },
      initData(){
        console.log("initData");
        var _this = this;
        this.getRequest("/employee/basic/basicdata").then(resp=> {
          if (resp && resp.status == 200) {
            var data = resp.data;
            _this.deps = data.deps;
            _this.positions = data.positions;
            _this.joblevels = data.joblevels;
            _this.emp.workID = data.workID;
          }
        })
      },
      showEditEmpView(row){
        console.log("showEditEmpView");
        console.log(row);
        this.dialogTitle = "编辑员工";
        this.emp = row;

        this.emp.departmentId = row.department.id;
        this.emp.departmentName = row.department.name;
        this.emp.jobLevelId = row.jobLevel.id;
        this.emp.posId = row.position.id;

        delete this.emp.workAge;
        delete this.emp.notWorkDate;
        // delete this.emp.salary;
        this.dialogVisible = true;
      },
      showAddEmpView(){
        this.dialogTitle = "添加员工";
        this.dialogVisible = true;
        var _this = this;
        this.getRequest("/employee/basic/maxWorkID").then(resp=> {
          if (resp && resp.status == 200) {
            _this.emp.workID = resp.data;
          }
        })
      },
      emptyEmpData(){
        this.emp = {
          name: '',
          highSea: '',
          province: '',
          city: '',
          county: '',
          website: '',
          remark: '',
          businessReceipt: '',
          businessScope: '',

          propertyValue: '',
          employeeNum: '',
          products: '',
          propertyType: '',

          email: '',
          phone: '',
          address: '',
          departmentId: '',
          departmentName: '所属部门...',
          jobLevelId: '',
          posId: '',
          industry: '',
          specialty: '',
          school: '',

          workState: '',
          workID: '',

          workAge: ''
        }
      }
    }
  };
</script>
<style>
  .el-dialog__body {
    padding-top: 0px;
    padding-bottom: 0px;
  }

  .slide-fade-enter-active {
    transition: all .8s ease;
  }

  .slide-fade-leave-active {
    transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
  }

  .slide-fade-enter, .slide-fade-leave-to {
    transform: translateX(10px);
    opacity: 0;
  }
</style>
