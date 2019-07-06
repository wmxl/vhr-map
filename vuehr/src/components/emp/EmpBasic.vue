<template>
  <div>
    <el-container>
      <el-header style="padding: 0px;display:flex;justify-content:space-between;align-items: center">
        <div style="display: inline">
          <el-input
            placeholder="请输入客户名称..."
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
          <!-- 高级搜索 -->
          <transition name="slide-fade">
            <div
              style="margin-bottom: 10px;border: 1px;border-radius: 5px;border-style: solid;padding: 5px 0px 5px 0px;box-sizing:border-box;border-color: #20a0ff"
              v-show="advanceSearchViewVisible">
              <el-row>
                <el-col :span="3">
                  <div>资产总计</div>
                  <el-input prefix-icon="el-icon-edit" v-model="propertyValueMin" size="mini" style="width: 120px"
                                placeholder="输入min"></el-input>
                  <el-input prefix-icon="el-icon-edit" v-model="propertyValueMax" size="mini" style="width: 120px"
                                placeholder="输入max"></el-input>
                    </el-foWrm-item>
                </el-col>

                <el-col :span="3">
                  <div>注册资本金</div>
                    <el-input prefix-icon="el-icon-edit" v-model="registerCapitalMin" size="mini" style="width: 120px"
                                placeholder="输入min"></el-input>  
                    <el-input prefix-icon="el-icon-edit" v-model="registerCapitalMax" size="mini" style="width: 120px"
                                placeholder="输入min"></el-input>  
                </el-col>

                <el-col :span="3">
                  <el-input
                    placeholder="请输入客户名称..."
                    clearable
                    @change="keywordsChange"
                    style="width: 160px;margin: 0px;padding: 0px;"
                    size="mini"
                    @keyup.enter.native="searchEmp"
                    prefix-icon="el-icon-search"
                    v-model="keywords">
                  </el-input>
                </el-col>

                <el-col :span="3">
                  <el-input
                    placeholder="请输入经营范围..."
                    clearable
                    @change="keywordsChange"
                    style="width: 160px;margin: 0px;padding: 0px;"
                    size="mini"
                    @keyup.enter.native="searchEmp"
                    prefix-icon="el-icon-search"
                    v-model="keywords_range">
                  </el-input>
                </el-col>
                <!-- <el-col :span="3">
                  <el-input
                    placeholder="请输入a..."
                    clearable
                    @change="keywordsChange"
                    style="width: 160px;margin: 0px;padding: 0px;"
                    size="mini"
                    @keyup.enter.native="searchEmp"
                    prefix-icon="el-icon-search"
                    v-model="a">
                  </el-input>
                </el-col> -->
                <el-col :span="3">
                  <el-input
                    placeholder="请输入主要产品..."
                    clearable
                    @change="keywordsChange"
                    style="width: 160px;margin: 0px;padding: 0px;"
                    size="mini"
                    @keyup.enter.native="searchEmp"
                    prefix-icon="el-icon-search"
                    v-model="keywords_products">
                  </el-input>
                </el-col>

              </el-row>

              <el-row style="margin-top: 10px">
                <el-col :span="5" :offset="4">
                  <el-button size="mini" @click="cancelSearch">取消</el-button>
                  <el-button icon="el-icon-search" type="primary" size="mini" @click="searchEmp">搜索</el-button>
                </el-col>
              </el-row>

            </div>
          </transition>
<!--          高级搜索 结束-->
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
              label="所属公海"
              width="50">
            </el-table-column>
            <el-table-column
              prop="customerId"
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
              label="地区（县）"
              align="left"
              width="50">
            </el-table-column>

            <el-table-column
              align="left"
              prop="industry"
              label="行业">
            </el-table-column>

            <el-table-column
              prop="address"
              width="220"
              align="left"
              label="地址">
            </el-table-column>

            <el-table-column
              prop="phone"
              width="100"
              label="联系电话">
            </el-table-column>

            <el-table-column
              prop="email"
              width="180"
              align="left"
              label="邮箱">
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
              width="250">
            </el-table-column>

            
            <el-table-column
              prop="registerCapital"
              label="注册资本金"
              align="left"
              width="50">
            </el-table-column>
            
            <el-table-column
              prop="corporateNature"
              label="公司性质"
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
              prop="oldName"
              label="曾用名"
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
              width="250">
            </el-table-column>

            <el-table-column
              prop="propertyType"
              label="资质类型"
              align="left"
              width="50">
            </el-table-column>            

            <el-table-column
              prop="giov"
              label="工业总产值"
              align="left"
              width="50">
            </el-table-column>   

            <el-table-column
              prop="creator"
              label="创建人"
              align="left"
              width="50">
            </el-table-column> 

            <el-table-column
              prop="relevantP"
              label="相关人"
              align="left"
              width="250">
            </el-table-column>    

            <el-table-column
              prop="relevantPAccount"
              label="相关人账号"
              align="left"
              width="250">
            </el-table-column>   

            <el-table-column
              prop="creatorAccount"
              label="创建人账号"
              align="left"
              width="50">
            </el-table-column> 

            <el-table-column
              prop="chargePAccount"
              label="负责人账号"
              align="left"
              width="250">
            </el-table-column> 

            <el-table-column
              prop="chargeP"
              label="负责人"
              align="left"
              width="250">
            </el-table-column> 

            <el-table-column
              prop="longitude"
              label="经度"
              align="left"
              width="50">
            </el-table-column> 

            <el-table-column
              prop="latitude"
              label="纬度"
              align="left"
              width="50">
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
<!--    添加和编辑数据-->
    <el-form :model="emp" :rules="rules" ref="addEmpForm" style="margin: 0px;padding: 0px;">
      <div style="text-align: left">
        <el-dialog  
        :title="dialogTitle"   
        style="padding: 0px;" 
        :close-on-click-modal="false" 
        :visible.sync="dialogVisible"          
        width="77%">

          <el-row> <!-- 第一行 -->
            <el-col :span="5">
              <div>
                <el-form-item label="客户名称:" prop="name">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.name" size="mini" style="width: 135px"
                            placeholder="输入客户名称.."></el-input>
                </el-form-item>
              </div>
            </el-col>

             <el-col :span="4">
              <div>
                <el-form-item label="所属公海:" prop="highSea">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.highSea" size="mini" style="width: 105px"
                            placeholder="输入公海.."></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="5">
              <div>
                <el-form-item label="客户编码:" prop="customerId">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.customerId" size="mini" style="width: 130px"
                            placeholder="输入客户编码.."></el-input>
                </el-form-item>
              </div>
            </el-col>
            
            <el-col :span="4">
              <div>
                <el-form-item label="省份:" prop="province">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.province" size="mini" style="width: 120px"
                            placeholder="输入省份.."></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>

          <el-row> <!-- 第二行 -->
            <el-col :span="4">
              <div>
                <el-form-item label="城市:" prop="city">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.city" size="mini" style="width: 120px"
                            placeholder="输入城市.."></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="5">
              <div>
                <el-form-item label="地区(县):" prop="county">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.county" size="mini" style="width: 150px"
                            placeholder="输入地区(县).."></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="4">
              <div>
                <el-form-item label="行业:" prop="industry">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.industry" size="mini" style="width: 135px"
                            placeholder="输入行业.."></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="6">
              <div>
                <el-form-item label="   地址:" prop="address">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.address" size="mini" style="width: 220px"
                            placeholder="输入地址..."></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>

          <el-row> <!-- 第三行 -->
            <el-col :span="5">
              <div>
                <el-form-item label="电话号码:" prop="phone">
                  <el-input prefix-icon="el-icon-phone" v-model="emp.phone" size="mini" style="width: 140px"
                            placeholder="输入电话号码..."></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="6">
              <div>
                <el-form-item label="电子邮箱:" prop="email">
                  <el-input prefix-icon="el-icon-message" v-model="emp.email" size="mini" style="width: 150px"
                            placeholder="电子邮箱地址..."></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="5">
              <div>
                <el-form-item label="网址:" prop="website">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.website" size="mini" style="width: 150px"
                            placeholder="输入industry"></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="5">
              <div>
                <el-form-item label="备注:" prop="remark">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.remark" size="mini" style="width: 150px"
                            placeholder="输入industry"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>

          <el-row> <!-- 第四行 -->
            <el-col :span="5">
              <div>
                <el-form-item label="营业收入:" prop="businessReceipt">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.businessReceipt" size="mini" style="width: 150px"
                            placeholder="输入industry"></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="5">
              <div>
                <el-form-item label="营业范围:" prop="businessScope">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.businessScope" size="mini" style="width: 150px"
                            placeholder="输入industry"></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="5">
              <div>
                <el-form-item label="注册资本金:" prop="registerCapital">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.registerCapital" size="mini" style="width: 150px"
                            placeholder="输入注册资本金"></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="5">
              <div>
                <el-form-item label="公司性质:" prop="corporateNature">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.corporateNature" size="mini" style="width: 150px"
                            placeholder="输入公司性质"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>

          <el-row> <!-- 第五行 -->
            <el-col :span="5">
              <div>
                <el-form-item label="资产总计:" prop="propertyValue">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.propertyValue" size="mini" style="width: 150px"
                            placeholder="输入industry"></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="5">
              <div>
                <el-form-item label="曾用名:" prop="oldName">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.oldName" size="mini" style="width: 150px"
                            placeholder="输入曾用名"></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="5">
              <div>
                <el-form-item label="从业总人数:" prop="employeeNum">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.employeeNum" size="mini" style="width: 150px"
                            placeholder="输入industry"></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="5">
              <div>
                <el-form-item label="主要产品:" prop="products">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.products" size="mini" style="width: 150px"
                            placeholder="输入industry"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row> <!-- 第6行 -->
            <el-col :span="5">
              <div>
                <el-form-item label="资质类型:" prop="propertyType">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.propertyType" size="mini" style="width: 150px"
                            placeholder="输入industry"></el-input>
                </el-form-item>
              </div>
            </el-col>
          
            <el-col :span="5">
              <div>
                <el-form-item label="工业总产值:" prop="giov">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.giov" size="mini" style="width: 150px"
                            placeholder="输入工业总产值"></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="5">
              <div>
                <el-form-item label="创建人:" prop="creator">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.creator" size="mini" style="width: 150px"
                            placeholder="输入创建人。。"></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="5">
              <div>
                <el-form-item label="相关人:" prop="relevantP">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.relevantP" size="mini" style="width: 150px"
                            placeholder="输入相关人"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>

          <el-row> <!-- 第七行 -->
            <el-col :span="5">
              <div>
                <el-form-item label="相关人账号:" prop="relevantPAccount">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.relevantPAccount" size="mini" style="width: 150px"
                            placeholder="输入相关人账号"></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="5">
              <div>
                <el-form-item label="创建人账号:" prop="creatorAccount">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.creatorAccount" size="mini" style="width: 150px"
                            placeholder="输入创建人账号"></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="5">
              <div>
                <el-form-item label="负责人账号:" prop="chargePAccount">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.chargePAccount" size="mini" style="width: 150px"
                            placeholder="输入负责人账号"></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="5">
              <div>
                <el-form-item label="负责人:" prop="chargeP">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.chargeP" size="mini" style="width: 150px"
                            placeholder="输入负责人"></el-input>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-row> <!-- 第8行 -->
            <el-col :span="5">
              <div>
                <el-form-item label="经度:" prop="longitude">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.longitude" size="mini" style="width: 150px"
                            placeholder="输入经度.."></el-input>
                </el-form-item>
              </div>
            </el-col>

            <el-col :span="5">
              <div>
                <el-form-item label="纬度:" prop="latitude">
                  <el-input prefix-icon="el-icon-edit" v-model="emp.latitude" size="mini" style="width: 150px"
                            placeholder="输入纬度.."></el-input>
                </el-form-item>
              </div>
            </el-col>
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
        a:'',
        keywords: '',
        keywords_range: '',
        keywords_products: '',
        propertyValueMin: '',
        propertyValueMax: '',
        registerCapitalMin: '',
        registerCapitalMax: '',
        fileUploadBtnText: '导入数据',
        faangledoubleup: 'fa-angle-double-up',
        faangledoubledown: 'fa-angle-double-down',
        dialogTitle: '',
        multipleSelection: [],
        depTextColor: '#c0c4cc',
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
          name: '无名小路的公司1',
          highSea: '',
          customerId: '',
          province: '',
          city: '',
          county: '',
          website: '',
          remark: '',
          businessReceipt: '',
          businessScope: '',
          registerCapital: '25000',
          corporateNature: '',
          propertyValue: '15000',
          oldName: '',
          employeeNum: '',
          products: '',
          propertyType: '',
          email: '1245667@qq.com',
          phone: '',
          address: '',
          industry: '',
          giov: '',
          creator: '',
          relevantP: '',
          relevantPAccount: '',
          creatorAccount: '',
          chargePAccount: '',
          chargeP: '',
          longitude: '111',
          latitude:'222'
        },
        rules: {
          name: [{required: true, message: '必填:姓名', trigger: 'blur'}],
          propertyValue: [{required: true, message: '必填:资产总计', trigger: 'blur'}],
          registerCapital: [{required: true, message: '必填:注册资本金', trigger: 'blur'}],
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
        this.propertyValueMin = '';
        this.propertyValueMax = '';
        this.registerCapitalMin = '';
        this.registerCapitalMax = '';
        this.loadEmps();
      },
      showAdvanceSearchView(){
        this.advanceSearchViewVisible = !this.advanceSearchViewVisible;
        this.keywords = '';
        this.a = '';
        this.keywords_range = '';
        this.keywords_products = '';
        if (!this.advanceSearchViewVisible) {
          this.emptyEmpData();
          this.propertyValueMin = '';
          this.propertyValueMax = '';
          this.registerCapitalMin = '';
          this.registerCapitalMax = '';
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
      //lemps 加载数据的方法
      loadEmps(){
        console.log("loadEmps:")
        var _this = this;
        console.log(this);
  
        this.tableLoading = true;
        this.getRequest("/employee/basic/emp?page=" + this.currentPage + "&size=10&keywords=" + this.keywords + "&keywords_range=" + this.keywords_range + "&a" +this.a + "&keywords_products=" + this.keywords_products + "&propertyValueMin=" + this.propertyValueMin + "&propertyValueMax=" + this.propertyValueMax + "&registerCapitalMin=" + this.registerCapitalMin + "&registerCapitalMax=" + this.registerCapitalMax).then(resp=> {
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
              this.tableLoading = true;
              this.putRequest("/employee/basic/emp", this.emp).then(resp=> {
                console.log("进入更新putRequest");
                console.log(resp.status)
                _this.tableLoading = false;
                if (resp && resp.status == 200) {
                  var data = resp.data;
                  _this.dialogVisible = false;
                  _this.emptyEmpData();
                  _this.loadEmps();
                }
              })
            } else {
              //添加
              console.log("进入添加");
              this.tableLoading = true;
              this.postRequest("/employee/basic/emp", this.emp).then(resp=> {
                console.log("进入添加posRequest");
                _this.tableLoading = false;
                console.log(resp.status);
                if (resp && resp.status == 200) {
                  console.log("进入添加if");
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
      initData(){
        console.log("initData");
        var _this = this;
        this.getRequest("/employee/basic/basicdata").then(resp=> {
          if (resp && resp.status == 200) {
            var data = resp.data;
            _this.deps = data.deps;
          }
        })
      },
      showEditEmpView(row){
        console.log("showEditEmpView");
        console.log(row);
        this.dialogTitle = "编辑员工";

        this.emp = row;
        this.dialogVisible = true;
      },
      showAddEmpView(){
        this.dialogTitle = "添加员工";
        this.dialogVisible = true;
        var _this = this;
      },
      emptyEmpData(){
        this.emp = {
          name: '无名2',
          highSea: '',
          customerId: '',
          province: '',
          city: '',
          county: '',
          website: '',
          remark: '',
          businessReceipt: '',
          businessScope: '',
          registerCapital: '25000',
          corporateNature: '',
          propertyValue: '15000',
          oldName: '',
          employeeNum: '',
          products: '',
          propertyType: '',
          email: '1245667@qq.com',
          phone: '',
          address: '',
          industry: '',
          giov: '',
          creator: '',
          relevantP: '',
          relevantPAccount: '',
          creatorAccount: '',
          chargePAccount: '',
          chargeP: '',
          longitude: '111',
          latitude:'222'
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
