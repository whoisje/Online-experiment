Component({
  data: {
    selected: 0,
    color: "#7A7E83",
    selectedColor: "#3cc51f",
    list: [{
      pagePath: "/pages/index/index",
      iconPath: "/image/basics.png",
      selectedIconPath: "/image/basics_cur.png",
      text: "首页"
    }, {
        pagePath: "/pages/course/course",
      iconPath: "/image/component.png",
        selectedIconPath: "/image/component_cur.png",
      text: "课程"
      }, {
        pagePath: "/pages/home/home",
        iconPath: "/image/about.png",
        selectedIconPath: "/image/about_cur.png",
        text: "我的"
      }]
  },
  attached() {
  },
  methods: {
    switchTab(e) {
      const data = e.currentTarget.dataset
      const url = data.path
      console.log(url)
      wx.switchTab({url})
      this.setData({
        selected: data.index
      })
    }
  }
})