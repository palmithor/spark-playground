import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {TodoService} from "./todo.service";


@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.scss'],
  providers: [TodoService]
})
export class TodoComponent implements OnInit {

  private path;

  private todos;
  private activeTasks;

  private newTodo;

  constructor(private todoService: TodoService, private route: ActivatedRoute) {
  }


  getTodos(query = '') {
    return this.todoService.get(query)
      .then(todos => {
        this.todos = todos;
        this.activeTasks = this.todos.filter(todo => !todo.isDone).length;
      });
  }

  addTodo() {
    this.todoService.add({title: this.newTodo, done: false})
      .then((todo) => {
        this.todos.unshift(todo);
        this.activeTasks++;
      }).then(() => this.newTodo = ''); // clear input form value
  }

  updateTodo(todo, newValue) {
    let oldValue = todo.title;
    todo.title = newValue;
    return this.todoService.put(todo)
      .then(() => {
        todo.editing = false;
      }).catch(() => {
        todo.editing = false;
        todo.title = oldValue;
      });
  }

  toggleComplete(todo) {
    todo.done = !todo.done;

    return this.todoService.put(todo)
      .then(() => {

      }).catch(() => {
        todo.done = !todo.done;
      });
  }

  destroyTodo(todo) {
    if (!todo.done) {
      this.activeTasks--;
    }

    let indexOfTodo = this.todos.indexOf(todo);

    this.todos.splice(indexOfTodo, 1);

    this.todoService.delete(todo.id)
      .then()
      .catch(() => {
        if (!todo.done) {
          this.activeTasks++;
        }
        this.todos.splice(indexOfTodo, 0, todo);
      });
  }

  clearCompleted() {
    //this.todoService.deleteCompleted().then(() => {
    //  return this.getTodos();
    //});
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.path = params['status'];
      this.getTodos(this.path);
    });
  }

}
