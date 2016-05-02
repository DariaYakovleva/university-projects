{-# LANGUAGE UnicodeSyntax, OverloadedStrings, NoImplicitPrelude, RankNTypes, FlexibleInstances #-}
 module MainLection04 where

 import Base

data List' a = Cons' a (List a) -- конструктор принимает a возвращает List  с добавленным элементом


data Cat = Cat
cats = Cons Cat cats
-- cats = Cons Cat (Cons Can cats)



data List a = Nil | Cons a (List a) -- конструктор принимает a возвращает List  с добавленным элементом

                        deriving Show


test = [1, 100, 200, 10]
test' = 1: 100:200: 10:[]
test' = Cons 1 $ Cons 100 $ Cons 200 $ Cons 10 Nil

(!) :: List a -> Int -> a
(!) [] n = error "aaaaa!"
(!) (a:as) 0 = a
(!) (_:as) n = as ! (n - 1)



-- map, zip, fold typeclass likeVector
-- ?? неявное подставление аргументов
-- instance как функцию с примером, потом instance с похожим примером
instance (LikeNumberClass a, LikeVectorClass v) => LikeVectorClass (v a)  where
(*) = undefined
(+) = undefined

data [a] = [] | a : [a] -- : - интфиксный Cons
--одномвязн цикл? список


