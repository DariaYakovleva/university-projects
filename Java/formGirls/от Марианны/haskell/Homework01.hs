{-# LANGUAGE NoImplicitPrelude, RankNTypes #-}
module Homework01 where

import Base
import Functions


data Vector2D a = Vector2D 
        { vx2 :: a
        , vy2 :: a }
                                

zip2D :: (a -> b -> c) -> Vector2D a -> Vector2D b -> Vector2D c
zip2D f v u = f (vx2 v) (vx2 u) `at` Vector2D $ f (vy2 v) (vy2 u) 
--f y y' `Vector2D` f x x'

map2D :: (a -> b) -> Vector2D a -> Vector2D b
map2D f v = (Vector2D `on` (f . (v `at`))) vx2 vy2

fold2D :: (a -> a -> a) -> Vector2D a -> a
fold2D f v = at vy2 ( vx2 `at` ((on f) . at) v)


data Vector3D a = Vector3D 
         { vx3 :: a
         , vy3 :: a 
         , vz3 :: a }
                                

zip3D :: (a -> b -> c) -> Vector3D a -> Vector3D b -> Vector3D c
zip3D f v u = f (vz3 v) (vz3 u) `at` Vector3D (f (vx3 v) (vx3 u)) $ f (vy3 v) (vy3 u) 

map3D :: (a -> b) -> Vector3D a -> Vector3D b
map3D f v = (on Vector3D (f . (v `at`))) vx3 vy3 ((f . (v `at`)) vz3)

fold3D :: (a -> a -> a) -> Vector3D a -> a
fold3D f v = f (vx3 v) $ f (vy3 v) (vz3 v) 

{-
fold3D :: (a -> a -> a) -> Vector3D a -> a
fold3D f (Vector3D x y z) = f x y `f` z
-}

class IsVectorClass v where
    isvMap :: (a -> b) -> v a -> v b
    isvFold :: (a -> a -> a) -> v a -> a
    isvZip :: (a -> b -> c) -> v a -> v b -> v c

instance IsVectorClass Vector2D where
    isvMap = map2D
    isvZip = zip2D
    isvFold = fold2D

instance IsVectorClass Vector3D where
    isvMap = map3D
    isvZip = zip3D
    isvFold = fold3D

class IsNumberClass a where
    (+) :: a -> a -> a
    (*) :: a -> a -> a

instance IsNumberClass Int where
    (+) = sumInt
    (*) = mulInt

instance IsNumberClass Float where
    (+) = sumFloat
    (*) = mulFloat

instance IsNumberClass Double where
    (+) = sumDouble
    (*) = mulDouble

innerProduct :: (IsVectorClass v, IsNumberClass a) => v a -> v a -> a
innerProduct = isvFold (+) $$ isvZip (*)

--innerProduct x y = isvFold (+) (isvZip (*) x y)
