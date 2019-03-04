// pages/home/home.js
var app = getApp();
Component({
  data:{
    studentName:"王文杰",
  },
  
  pageLifetimes: {
    show() {
      app.checkState();
      if (typeof this.getTabBar === 'function' &&
        this.getTabBar()) {
        this.getTabBar().setData({
          selected: 2
        })
      }
    }
  },
  properties: {

  },
  methods: {


  }
})
