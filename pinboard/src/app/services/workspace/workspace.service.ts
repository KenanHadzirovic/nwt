import { Injectable, Output, EventEmitter } from '@angular/core';
import { Post } from 'src/app/models/post';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs/internal/Observable';
import { Workspace } from 'src/app/models/workspace';

@Injectable({
  providedIn: 'root'
})
export class WorkspaceService {

  constructor(private http: HttpClient) { }

  posts: Post[];
  workspaces: Workspace[];

  @Output() postchange: EventEmitter<Post[]> = new EventEmitter();
  @Output() workspaceChange: EventEmitter<Workspace[]> = new EventEmitter();

  addPost(post: Post) {
    this.posts.push(post);
    this.postchange.emit(this.posts);
  }

  public getPosts = (): Post[] => {
    this.http.get<Post[]>(environment.postServiceUrl, {
      headers: {'Access-Control-Allow-Origin':'*'}
    }).subscribe((data: Post[]) => {
      this.posts = data;
      this.postchange.emit(this.posts);
    });
    return this.posts;
  }

  public getWorkspaces = (): Workspace[] => {
    this.http.get<Workspace[]>(environment.workspaceServiceUrl, {
      headers: {'Access-Control-Allow-Origin':'*'}
    }).subscribe((data: Workspace[]) => {
      this.workspaces = data;
      this.workspaceChange.emit(this.workspaces);
    });
    return this.workspaces;
  }

  public removePosts(id: number) {
    this.posts.splice(this.posts.findIndex(x => x.id == id), 1);
    this.postchange.emit(this.posts);
  }

  public addWorkspace(workspace: Workspace)
  {
    return this.http.post(environment.workspaceServiceUrl + '/owner/1', workspace);
  }

  public removeWorkspace(index: number) {
    this.workspaces.splice(this.workspaces.findIndex(x => x.id == index), 1);
    this.workspaceChange.emit(this.workspaces);
    this.http.delete(environment.workspaceServiceUrl + '/' + index).subscribe();
  }
}
