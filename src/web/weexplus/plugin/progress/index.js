import progress from './progress.vue'

let $vm

const ProgressPlugin = {
  install (vue) {
    const Progressvm = vue.extend(ProgressPlugin)

    if (!$vm) {
      $vm = new Progressvm({
        el: document.createElement('div')
      })
      document.body.appendChild($vm.$el)
    }

    // all Vux's plugins are included in this.$vux
    if (!vue.$wxp) {
      vue.$wxp = {}
    }
    vue.$wxp.progress = $vm
    window.$wxp.progress= $vm
    vue.mixin({
      created: function () {
        this.$wxp = vue.$wxp
      }
    })
  }
}
const install = ProgressPlugin.install
export {
  progress,
  install
}
