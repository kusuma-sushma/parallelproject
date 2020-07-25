import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RoutingguardService } from '../routingguard.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  error: String;
  message: String;
  constructor(private routingGuardService: RoutingguardService, private route: ActivatedRoute, private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  loginUser(form: NgForm) {
   return this.authService.login(form.value).subscribe(response => {
      console.log(response);
      if (response.error) {
        this.error = "Credentials which are given is invalid, failed to login";
        setTimeout(() => {
          this.error = null;
        }, 5000);
        form.reset();
      }  else {
        form.reset();
        this.routingGuardService.setTempSession();
        if (response.userInfo.role == 'student') {
          localStorage.setItem('userDetails',JSON.stringify(response.userInfo));
          localStorage.setItem('role', response.userInfo.role);
          this.router.navigateByUrl('/allBooks');
        } else if (response.userInfo.role == 'admin') {
          localStorage.setItem('role', response.userInfo.role);
          this.router.navigateByUrl('/allBooks');
        } else {
          this.message=response.message;
          setTimeout(() => {
            this.error = null;
          }, 5000);

        }

      }


    });
  }

}
