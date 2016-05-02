{-# LANGUAGE NoImplicitPrelude, RankNTypes #-}

module Homework00 where

import Base

data IsVector v = IsVector 
    { isvZip :: forall a b c. (a -> b -> c) -> v a -> v b -> v c
    , isvMap :: forall a b. (a -> b) -> v a -> v b
    , isvFold :: forall a. (a -> a -> a) -> v a -> a }

data Vector2D a = Vector2D a a

zip2D :: (a -> b -> c) -> Vector2D a -> Vector2D b -> Vector2D c
zip2D f (Vector2D x y) (Vector2D x' y') = Vector2D (f x x') (f y y')

map2D :: (a -> b) -> Vector2D a -> Vector2D b
map2D f (Vector2D x y) = Vector2D (f x) (f y)

fold2D :: (a -> a -> a) -> Vector2D a -> a
fold2D f (Vector2D x y) = f x y

isVector2D :: IsVector Vector2D 
isVector2D = IsVector zip2D map2D fold2D 



data Vector3D a = Vector3D a a a

zip3D :: (a -> b -> c) -> Vector3D a -> Vector3D b -> Vector3D c
zip3D f (Vector3D x y z) (Vector3D x' y' z') = Vector3D (f x x') (f y y') (f z z')

map3D :: (a -> b) -> Vector3D a -> Vector3D b
map3D f (Vector3D x y z) = Vector3D (f x) (f y) (f z)

fold3D :: (a -> a -> a) -> Vector3D a -> a
fold3D f (Vector3D x y z) = f x (f y z)

isVector3D :: IsVector Vector3D  
isVector3D = IsVector zip3D map3D fold3D

data IsNumber a = IsNumber
    { (+) :: a -> a -> a
    , (*) :: a -> a -> a }

mulNumber = (*)
sumNumber = (+)

isNumberInt :: IsNumber Int
isNumberInt = IsNumber sumInt mulInt

isNumberFloat :: IsNumber Float
isNumberFloat = IsNumber sumFloat mulFloat

isNumberDouble :: IsNumber Double
isNumberDouble = IsNumber sumDouble mulDouble

innerScalar :: IsVector v -> IsNumber a -> v a -> v a -> a 
innerScalar (IsVector zip map fold) (IsNumber (+) (*)) v u  = fold (+) (zip (*) v u) 
