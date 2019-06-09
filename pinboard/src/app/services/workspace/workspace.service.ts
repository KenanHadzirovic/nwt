import { Injectable, Output, EventEmitter } from '@angular/core';
import { Post } from 'src/app/models/post';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class WorkspaceService {

  constructor(private http: HttpClient) { }


  postUrl = environment.postServiceUrl;
  posts: Post[];

  @Output() change: EventEmitter<Post[]> = new EventEmitter();

  addPost(post: Post) {
    this.posts.push(post);
    this.change.emit(this.posts);
  }

  public getPosts = (): Post[] => {
    this.http.get<Post[]>(this.postUrl, {
      headers: {'Access-Control-Allow-Origin':'*'}
    }).subscribe((data: Post[]) => {
      this.posts = data;
      this.change.emit(this.posts);
    });
    return this.posts;
  }

  public removePosts(id: number) {
    this.posts.splice(this.posts.findIndex(x => x.id == id), 1);
    this.change.emit(this.posts);
  }
}
