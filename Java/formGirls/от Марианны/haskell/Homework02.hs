{-# LANGUAGE NoImplicitPrelude, OverloadedStrings, FlexibleInstances #-}

module Homework02 where

import qualified Homework00 as H00
import Homework01
import Base

data List a = Nil | Cons a (List a)

foldList :: (a -> a -> a) -> [a] -> a
foldList f [] = error "zero"
foldList f (x:[]) = x
foldList f (x:xs) = f x (foldList f xs)

zipList :: (a -> b -> c) -> [a] -> [b] -> [c]
zipList f [] [] = []
zipList f  _ [] = error "zero"
zipList f [] _ = error "zero"
zipList f (x:xs) (y:ys) = (f x y):(zipList f xs ys)


mapList :: (a -> b) -> [a] -> [b]
mapList f [] = []
mapList f (x:xs) = (f x): (mapList f xs)


instance IsVectorClass [] where
    isvMap = mapList
    isvZip = zipList
    isvFold = foldList

instance (IsNumberClass a, IsVectorClass v) => IsNumberClass (v a) where
    (+) = isvZip (+)
    (*) = isvZip (*)


isVectorList :: H00.IsVector []
isVectorList = H00.IsVector zipList mapList foldList

isNumberIsVector :: H00.IsNumber a -> H00.IsVector v -> H00.IsNumber (v a)
isNumberIsVector (H00.IsNumber (+) (*)) (H00.IsVector zip map fold) = H00.IsNumber (zip (+)) (zip (*))

isNumberListInt = isNumberIsVector H00.isNumberInt isVectorList

mulList = (H00.mulNumber) isNumberListInt
sumList = (H00.sumNumber) isNumberListInt


