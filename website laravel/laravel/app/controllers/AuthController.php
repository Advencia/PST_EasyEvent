<?php

class AuthController extends Controller {
    public function getLogin() {
    	return View::make('home.index');
    }
	
	public function postLogin(){
		$rules = array('username' => 'required', 'password' => 'required');
		$validator = Validator::make(Input::all(), $rules);




	if($validator->fails()) {
		return Redirect::route('login')->withErrors($validator);
	}
  
       $auth = Auth::attempt(array(
          'username' => Input::get('username'),
          'password' => Input::get('password')


       	), false);

      if(!$auth) {

      	  return Redirect::route('login')->withErrors(array(
              'Identifiant erroné'

      	  	));
      }

      return Redirect::to('/user');





	}

public function getLogout()
  {
    Auth::logout(); 
    return Redirect::route('home');
  }


}