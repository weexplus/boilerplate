function Stack() {
  this.dataStore = [];
  this.top = 0;//栈顶元素的位置
  this.push = push;
  this.pop = pop;
  this.peek = peek;
  this.length=length;
  this.clear=clear;
}

function push(element) {//进栈
  this.dataStore[this.top++] = element;
}

function pop() {//出栈
  return this.dataStore[--this.top];
}

function peek() {//栈顶元素
  return this.dataStore[this.top-1];
}

function length() {
  return this.top;
}

function clear() {
  this.top = 0;
}

export default Stack