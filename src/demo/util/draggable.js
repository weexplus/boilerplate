const supportTouch = WXEnvironment.platform === 'Web' && 'ontouchstart' in window
export class Draggable {
  constructor (element, option) {
    if (WXEnvironment.platform === 'Web') {
      this.isDragging = false

      const _getOffset = function (e) {
        if (supportTouch) {
          return {
            x: e.changedTouches[0].pageX,
            y: e.changedTouches[0].pageY
          }
        } else {
          return {
            x: e.pageX,
            y: e.pageY
          }
        }
      }

      const onDragStart = function (e) {
        this.isDragging = true
        document.onselectstart = () => false
        document.ondragstart = () => false
        if (!supportTouch) {
          element.addEventListener('mousemove', onDragMove, false)
          element.addEventListener('mouseup', onDragEnd, false)
          element.addEventListener('mouseout', onDragEnd, false)
        }

        if (option.start) {
          option.start(_getOffset(e))
        }
      }

      const onDragMove = function (e) {
        if (option.move) {
          option.move(_getOffset(e))
        }
      }

      const onDragEnd = function (e) {
        this.isDragging = false
        document.onselectstart = null
        document.ondragstart = null

        if (!supportTouch) {
          element.removeEventListener('mousemove', onDragMove, false)
          element.removeEventListener('mouseup', onDragEnd, false)
          element.removeEventListener('mouseout', onDragEnd, false)
        }

        if (option.end) {
          option.end(_getOffset(e))
        }
      }

      element.addEventListener(supportTouch ? 'touchstart' : 'mousedown', onDragStart, false)

      if (supportTouch) {
        element.addEventListener('touchmove', onDragMove, false)
        element.addEventListener('touchend', onDragEnd, false)
        element.addEventListener('touchcancel', onDragEnd, false)
      }

      this.removeListener = function () {
        element.removeEventListener(supportTouch ? 'touchstart' : 'mousedown', onDragStart, false)
        if (supportTouch) {
          element.removeEventListener('touchmove', onDragMove, false)
          element.removeEventListener('touchend', onDragEnd, false)
          element.removeEventListener('touchcancel', onDragEnd, false)
        }
      }
    }
  }
}
