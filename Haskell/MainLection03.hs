{-# LANGUAGE UnicodeSyntax, OverloadedStrings, NoImplicitPrelude, RankNTypes #-}
 module MainLection03 where

 import Base


class LikeVectorClass v where
    vMap :: (a -> b) -> v a -> v b
    vZip ::  (a -> b -> c) -> v a -> v b -> v c
    vFold :: (a -> a -> a) -> v a -> a

instance LikeVectorClass Vector2D where
    vMap = mapVec2D
    vZip = zipVec2D
    vFold = fold2D

class LikeNumberClass a where
(+) :: a -> a -> a
(*) :: a -> a -> a

instance likenumberclass float where
    (+) = sumfloat
    (*) = mulfloat

instance likenumberclass float where
    (+) = sumfloat
    (*) = mulfloat


ginnerProduct :: (LikeVectorClass v, LikeNumberClass a) => v a -> v a -> a
-- чтобы ичскало из контекста чтобы знал, откуда брать инстнсы
ginnerProduct x y = vFold (+) (vZip (*) x y)

x :: Float
x = ginnerProduct (Vector2D 1.0 1) (Vector2D 1 1)


plus = \x -> x
x = 1 'at' plus -- x = at 1 plus
g :: Float -> Int
f = hole
g :: Int -> Float -> Double
g = hole
h :: Int -> Int -> Float
h - hole


x' = f $ g 1 $ h 1 2


f x = vFold (+) . vZip (*) x -- f x =(.)((vFold (+)) ((vZip (*)) x)) -- x = \y - > vFold (+) (vZip(*) x y)


fromIntToFloat :: Int -> Float
fromIntToFloat = hole


data Person = PersonDesc
    { name :: String
    , money :: Float
    }

comparePerson :: Person -> Person -> Ordering
comparePerson = compareFloat 'on' money


compareFloat :: Float -> Float -> Ordering
compareFloat = hole

compareInt :: int -> Int -> Ordering
compareInt = on compareFloat fromIntToFloat
compareInt = compareFloat 'on' fromIntToFloat


round :: Float -> Int
round = hole

sumRound :: Float -> Float -> Int
sumRound x y = ($$) round sumFloat x y

sumRound = ((.) round) . sumFloat
sumRound x y = ((.) round) . sumFloat x y
