<?php

class HomeController extends BaseController {

	public function getIndex() {
		return View::make('home.index');
	}
      public function postIndex() {
      	$username = Input::get('username');
         $password = Input::get('password');
         if (Auth::attempt(['username' => $username, 'password' => $password]))
         {
             return Redirect::intended('/user');
         }
         return Redirect::back() 
            ->withInput()
            ->withErrors('Identifiants incorrectes');
      }
     
      public function getLogin()
       {
         return Redicrect::to('/');
       }
      public function getLogout()
      {
         Auth::logout();
         return Redirect::to('/');
      }
      public function getInscriptions() {
         return View::make('inscriptions');
      }
       
}
