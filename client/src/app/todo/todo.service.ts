import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import "rxjs/add/operator/toPromise";
import {Todo} from "./todo";


@Injectable()
export class TodoService {


  constructor(private http: Http) {
  }

  private baseUrl = 'http://localhost:4567/api/v1/todos';

  get(query = ''): Promise<Todo[]> {
    return this.http.get(this.baseUrl)
      .toPromise()
      .then(response => response.json().data as Todo[])
      .catch(this.handleError);
  }

  add(data): Promise<Todo> {
    return this.http.post(this.baseUrl, data)
      .toPromise()
      .then(response => response.json().data as Todo)
      .catch(this.handleError);
  }

  put(todo) {
    return this.http.put(this.baseUrl + '/' + todo.id, {
      title: todo.title,
      due: todo.due,
      done: todo.done
    })
      .toPromise()
      .then(response => response.json().data as Todo)
      .catch(this.handleError);
  }

  delete(id) {
    return this.http.delete(this.baseUrl + '/' + id)
      .toPromise()
      .then()
      .catch(this.handleError);
  }

  //deleteCompleted() {
  //  return new Promise(resolve => {
  //    todos = todos.filter(todo => !todo.done);
  //    resolve(todos);
  //  });
  //}

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
