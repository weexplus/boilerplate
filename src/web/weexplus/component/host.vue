<template>
    <div class="root" ref="root">
        <slot></slot>
    </div>
</template>
<script>
  export default{
    props: {
      index:{
        default:1
      }
    },
    data () {
      return {}
    },
    methods: {
      reset(index,load){
        for(let i=0;i< this.$refs.root.children.length;i++)
        {
          let c=this.$refs.root.children[i]
          c.style.position='absolute'
          c.style.top='0'
          c.style.bottom='0'
          c.style.left='0'
          c.style.right='0'
          c.style.display= index==i?'block':'none'
          if(load){
            var evt = new Event('load')
            if(window.route)
            {
              evt.param =window.route.params
            }
            c.dispatchEvent(evt)
          }
        }
        var evt = new Event('show')
        evt.index=index
        this.$refs.root.children[index].dispatchEvent(evt)
      }
    },
    created () {
    },
    watch:{
      index(current,old){
         this.reset(current)
      }
    },
    mounted(){
      this.reset(this.index,true)
    }
  }
</script>
<style scoped>
    .root{
        position: relative;
        flex: 1;
        background-color: red;
    }
</style>
