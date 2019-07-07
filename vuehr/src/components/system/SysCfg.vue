<template>
<div style="position: relative;width: 100%;height: 100%;">
  <div id="container" style="width:100%; height:100%"></div>
  <div id="panel"></div>
  <div id="myPageTop" style="position: absolute;top: 20px;right: 50px;background: #fff;">
    <table>
      <tr>
        <td>
          <label>请输入关键字：</label>
        </td>
      </tr>
      <tr>
        <td>
          <input v-model="input" id="tipinput"/><button @click="searchAddress">搜索</button>
        </td>
      </tr>
    </table>
    <p>附近</p>
    <select class="choice" v-on:change="indexSelect($event)">
          <option value='100000'>10km</option>
          <option value='200000'>20km</option>
          <option value='300000'>30km</option>
    </select>
  </div>   
  <div id="luenceSearch" style="position: absolute;top: 20px;right: 1100px;background: #fff;">
      <label>请输入luence关键字：</label>
      <input v-model="keyword" id="input_luence_id"/><button @click="searchAddress_luence">搜索</button>
    </div>
  </div>   
</template>

<script>
import AMap from 'AMap';
import AMapUI from 'AMapUI';
  var map, marker, placeSearch;
  export default {
    name: "AMap",
    data: function(){
      return {
        input: '',
        keyword: ''
      }
    },
    mounted: function () {
      this.AMapInit()
      this.showMark()
    },
    methods: {
      AMapInit: function () {
        map = new AMap.Map('container', {
          resizeEnable: true,
          zoom: 11
        })
        AMap.service(["AMap.PlaceSearch"], function () {
          //构造地点查询类
          placeSearch = new AMap.PlaceSearch({
            pageSize: 5, // 单页显示结果条数
            pageIndex: 1, // 页码
            citylimit: false,  //是否强制限制在设置的城市内搜索
            map: map, // 展现结果的地图实例
            panel: "panel", // 结果列表将在此容器中进行展示。
            autoFitView: true, // 是否自动调整地图视野使绘制的 Marker点都处于视口的可见范围
            renderStyle: 'default'
          });
 
        });
        // 添加列表点选监听事件
        AMap.event.addListener(placeSearch, "selectChanged", this.selectAddress);
        map.plugin(["AMap.Scale"], function() {
            map.addControl(new AMap.Scale());
        });
        map.plugin(["AMap.ToolBar"], function() {
            map.addControl(new AMap.ToolBar());
        });
        map.plugin(["AMap.Geolocation"], function() {
            map.addControl(new AMap.Geolocation());
        }); 
      },
      searchAddress() {
        placeSearch.search(this.input);
 
      },

      searchAddress_luence() {
        var _this = this;
        this.getRequest("/company/search?keyword=" + this.keyword).then(resp=> {
          console.log("&keyword=" + this.keyword)

          console.log(resp);
          console.log(resp.data);

          if (resp) {
          var data = resp.data;
            _this.list = data.com;
            console.log(_this.list)
          }
        })
      },

      selectAddress(e) {
        //这里获得点选地点的经纬度
        let location = e.selected.data.location;
        console.log('lng',location.lng);
        console.log('lat',location.lat);
        // Do Something
      },
      indexSelect(event){
      	this.AMapInit();
      	var _this = this;
        this.getRequest("/company/list").then(resp=> {
          if (resp) {
          var data = resp.data;
            _this.list = data.com;
          }
        	var infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30)});
        	var center = map.getCenter();
    		for (var i = 0; i < _this.list.length; i++) {
    			let lngLat = [parseFloat(_this.list[i].longitude), parseFloat(_this.list[i].latitude)];
        		var distance = Math.round(center.distance(lngLat));
        		if(distance<event.target.value){
        			var marker = new AMap.Marker({
            		position: lngLat,
            		map: map
        		});

        		var info = [];
        		info.push("<div class='input-card content-window-card'><div><img style=\"float:left;\" src=\" https://webapi.amap.com/images/autonavi.png \"/></div> ");
        		info.push("<div style=\"padding:7px 0px 0px 0px;\"><h4>公司信息</h4>");
        		info.push("<p class='input-item'>公司名称 :"+_this.list[i].name+"</p>");
        		info.push("<p class='input-item'>公司id :"+_this.list[i].companyId+"</p>");
        		info.push("<p class='input-item'>地址 :"+_this.list[i].address+"</p></div></div>");
        		marker.content = info.join("");
        		marker.on('click', markerClick);
        		marker.emit('click', {target: marker});
        		}
        		
        	}
        	
    		function markerClick(e) {
        		infoWindow.setContent(e.target.content);
        		infoWindow.open(map, e.target.getPosition());
        	}
        })
      },
      showMark(){
      var _this = this;
        this.getRequest("/company/list").then(resp=> {
          if (resp) {
          var data = resp.data;
            _this.list = data.com;
          }
        	var infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30)});
        	var center = map.getCenter();
    		for (var i = 0; i < _this.list.length; i++) {
    			let lngLat = [parseFloat(_this.list[i].longitude), parseFloat(_this.list[i].latitude)];
					var marker = new AMap.Marker({
            		position: lngLat,
            		map: map
        		});
        		var info = [];
        		info.push("<div class='input-card content-window-card'><div><img style=\"float:left;\" src=\" https://webapi.amap.com/images/autonavi.png \"/></div> ");
        		info.push("<div style=\"padding:7px 0px 0px 0px;\"><h4>公司信息</h4>");
        		info.push("<p class='input-item'>公司名称 :"+_this.list[i].name+"</p>");
        		info.push("<p class='input-item'>公司id :"+_this.list[i].companyId+"</p>");
        		info.push("<p class='input-item'>地址 :"+_this.list[i].address+"</p></div></div>");
        		marker.content = info.join("");
        		marker.on('click', markerClick);
        		marker.emit('click', {target: marker});	
        	}       	
    		function markerClick(e) {
        		infoWindow.setContent(e.target.content);
        		infoWindow.open(map, e.target.getPosition());
        	}
        })
      }
    }
  }
</script>
<style type="text/css">
  #panel {
    position: absolute;
    background-color: white;
    max-height: 90%;
    overflow-y: auto;
    top: 80px;
    right: 10px;
    width: 280px;
  }
</style>