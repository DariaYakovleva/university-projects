{-# LANGUAGE UnicodeSyntax, OverloadedStrings, NoImplicitPrelude, RankNTypes #-}
 module MainLection02 where

 import Base

 
foo = sumInt 2 hole
foo' = 2 'sumInt' Hole 
-- первый аргумент до названия функции(интфиксная функци)

foo'' = (+) 2 2

foo''' = (\x -> x 'sumInt' 1) 2
foo'''' = \x y -> x 'sumInt' y
foo''''' x y = x 'sumInt' y

bar = foo''''' 1

data Ord = LT | EQ | GT

data Ordering' =  LT' | EQ' | GT'

bar' = \x -> case x of
    LT -> -1
    EQ -> 0
    GT -> 1

data Intersection a = None | One a | Two a a

type II = Intersection Int

testI :: II
testI = None
testI2 :: II
test2 = One 1
test3 :: II
test2 = Two 1 2

first :: II -> Int
first None = 0
first (One x) = x
first (two x y) = x

data Vector2DInt = Vector2DInt Int Int
test' = Vector2DInt 1 2

data Vector2DFloat = Vector2DFloat Float Float
test' = Vector2DFloat 1.1 2.1

data Vector2D a = Vector2D a a


mapVec :: (a -> b) -> Vector2D a -> Vector2D b
mapVec f (Vector2D x y) = Vector2d (f x) (f y)


zipVecFloat :: (Float -> Float -> Float) -> (Vector2DFloat -> Vector2DFloat) -> Vector2DFloat
zipVecFloat f (Vector2D x y) (Vector2D x' y') = Vector2D (f x x') (f y y')



zipVec :: (a -> b -> c) -> (Vector2D a -> Vector2D b) -> Vector2D c
zipVec f (Vector2D x y) (Vector2D x' y') = Vector2D (f x x') (f y y')

foldVec :: (a -> a -> b) -. Vector2D a -> b
foldVec f (Vector2D x y) = f x y

data Vector3D = Vector3D a a a

mapVec3D :: (a -> b) -> Vecto3D a -> Vector3D b
mapVec3D f (Vector2D x y z) = Vector2D (f x) (f y) (f z)


zipVec3D :: (a -> b -> c) -> (Vector3D a -> Vector3D b) -> Vector3D c
zipVec3D f (Vector3D x y z) (Vector3D x' y' z') = Vector3D (f x x') (f y y')(f z z')

foldrVec3D :: (a -> b -> c) -> Vector3D a -> b
foldrVec3D f  Vector3D x y z) =  f x (f y z)



foldrVec3D :: (a -> a -> a) -> Vector3D a -> a
foldrVec3D f  Vector3D x y z) =  f x (f y z)


innerProduct :: Vector2D Float -> Vector2D Float -> Float
innerProduct v1 v2 = foldVec2D sumFloat (zipVec2D mulFloat v1 v2)


innerProduct :: Vector3D Float -> Vector3D Float -> Float
innerProduct v1 v2 = foldVec3D sumFloat (zipVec3D mulFloat v1 v2)

data IsVector v a  = IsVector 
                                {
                                    isvMap :: forall a b . (a -> b) -> v a -> v b
                                    , isvZip :: forall a b c .(a -> b -> c) -> v a _. v b -> v c
                                    , isvFold :: forall a .(a -> a -> a) -> v a -> a
                                }
vector2DisVector :: IsVector Vector2D
vector2DisVector = IsVector mapVec2D zipVec2D foldVec2D

innerProduct :: IsVector v -> v Float -> v Float -> Float
innerProduct lv v1 v2 = isvFold lv sumFloat (isvZip lv mulFloat v1 v2)
innerProduct2D' = innerProduct vec2DisVector


Домашнее задание написать вектора impl Vector 2 3  isVector interface с помощью Base 
внутри
number
число с интерфейсом плюс умножить

innerProduct для какого-то гавна


 test2 = zipVec (\x y -> gtFloat x y)
tee1 :: Vector2D Float
tee1 = Vector2D 1 2

printVector2DBool :: Vector2DBool -> String
printVector2DBool (Vector2D x y) = fromBoolToString x

test2' = PrintVector2DBool (test2 tee1 tee1)

sumVecFloat :: (Vector2DFloat -> Vector2DFloat) -> Vector2DFloat
sumVecFloat = zipVecFloat SumFloat
--sumVecFloat (Vector2D x y) (Vector2D x' y') = Vector2D
 --                                       (sumFloat x x')
 --                                        (sumFloat y y')
mulVecFloat :: (Vector2DFloat -> Vector2DFloat) -> Vector2DFloat
sumVecFloat = zipVecFloat MulFloat
--mulVecFloat (Vector2D x y) (Vector2D x' y') = Vector2D
--                                         (mulFloat x x')
--                                         (mulFloat y y')
                        



