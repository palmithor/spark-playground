export class Todo {
  id: number;
  title: string = '';
  done: boolean = false;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
