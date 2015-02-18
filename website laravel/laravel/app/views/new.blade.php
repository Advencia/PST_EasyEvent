@extends('layouts.main')

@section('content')
   <h1>Création de nouvelles tache</h1>
 
    @foreach ($errors->all() as $error)
        <p class="error">{{ $error }}</p>
    @endforeach

   {{ Form::open() }}
       <input type="text" name="name" placeholder="Le nom de la tache" />
       <input type="submit" value="Créer" /> 
   {{ Form::close() }}

@stop