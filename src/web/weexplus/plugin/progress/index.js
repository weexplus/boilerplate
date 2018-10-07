import load from './index.vue'
let $vm

const HLoadPlugin = {
  install (vue) {
    const HLoading = vue.extend(load)

    if (!$vm) {
      $vm = new HLoading({
        el: document.createElement('div')
      })
      document.body.appendChild($vm.$el)
    }

    const loading = {
      show (txt,cancel) {
        if (!$vm) {
          $vm = new HLoading({
            el: document.createElement('div')
          })
          document.body.appendChild($vm.$el)
        }
        $vm.show(txt,cancel)
      },

      hide () {
        setTimeout(() => {
          $vm.hide()
        }, 100)
      }
    }

    // all Vux's plugins are included in this.$vux
    if (!window.$wxp) {
      window.$wxp = {
        loading
      }
    } else {
      window.$wxp.loading = loading
    }

  }
}

export default HLoadPlugin
const install = HLoadPlugin.install
export { load, install }
