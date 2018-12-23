<template>
    <div style="align-items: center;justify-content: center">
        <text>{{txt}}</text>
       <!--<zoomimage style="width: 500px;height: 500px;background-color: red"></zoomimage>-->


    </div>
</template>
<script>
  export default{
    props: {},
    data () {
      return {
        items:[],
        src:'root:file/index.html',
        txt:''
      }
    },
    methods: {

      okk(){
        let im = weex.requireModule('nim')
        let p={account: '11'}
        p.navBarBgColor='#ff553e'
        p.theme='white'
        im.openP2P(p)
      },
      chathello(){
        let im = weex.requireModule('nim')
        let p={account: 'helloworld1'}
        p.navBarBgColor='#ff553e'
        p.theme='white'
        im.openP2P(p)
        this.okk()
      },
      chat11(){
        let im = weex.requireModule('nim')
//        let p={account: '11'}
//        p.navBarBgColor='#ff553e'
//        p.theme='white'
//        im.openP2P(p)
        im.recent((res)=>{
           this.items=res
        })
      },
      tenv(){

        this.ok()

      },
      ok(){
//        this.toast('sss')

        this.chat()
      },
      chat(){
        let im = weex.requireModule('nim')
        let p={account: 'helloworld1'}
        p.navBarBgColor='#ff553e'
        p.theme='white'
        im.openP2P(p)
      },
      onLoad(){
//        let im = weex.requireModule('nim')
//        im.regist({appKey:'86984f84905337af232230f9e140b113'})
//        im.login({account: 'helloworld1', token: '7c2ad9cedfa2971efbbf58fbd1e67378'}, (res) => {
//          this.alert('helloworld1登陆成功')
//
//        })
      }

    },
    created () {
      this.txt='🍷被朋友深夜放毒\n'
      +'我给圈友们也来一个&\n'
      +'火锅配莫斯卡托粉红起泡酒\n'
      +'好会享受，一群人喝得好欢！\n'
      +'关键时刻还来放毒\n'
      +'地主家没有点余粮怎么得了！\n'
      +'#原产原装原标#\n'
      +'澳洲Robert Oatley，蝉联7年James Halliday五星级酒庄！\n'
      +'作为Robert Oatley中国唯一官方合作伙伴，独家授权，原产原装原标，酒庄直发ABM，质量品质绝对保障！找我绝对没错啦！'
    }
  }
</script>
<style scoped>
    .indicator {
        width: 750px;
        height: 316px;
        position: absolute;
        item-color: #777777;
        item-selected-color: #ffffff;
        item-size: 20px;
        top: 120px;
        left: 1px;
    }

    .slider {
        width: 750px;
        height: 316px;
    }

    .frame {
        width: 750px;
        height: 316px;
        position: relative;
    }

    .image {
        width: 750px;
        height: 316px;
    }
</style>
