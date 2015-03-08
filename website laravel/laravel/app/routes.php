<?php
Route::resource('/user', 'UserController');
//Route::controller('/', 'HomeController');
Route::get('/inscriptions', array('as' => 'inscriptions', 'uses' => 'HomeController@getInscriptions'));
Route::post('/inscriptions', array('uses' => 'UserController@store'))->before('csrf');
Route::get('/login', array('as' => 'login', 'uses' => 'AuthController@getLogin'));
Route::post('login', array('uses' => 'AuthController@postLogin'));
Route::get('/edit', array('as' => 'edit', 'uses' => 'UserController@edit'));