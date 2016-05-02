{-# LANGUAGE NoImplicitPrelude, RankNTypes #-}

import Prelude hiding (map, zipWith)

data List a = Nil | Cons a (List a) -- конструктор принимает a возвращает List  с добавленным элементом


map' :: (a -> b) -> [a] -> [b]
map' f []    = [] 
map' f (x:xs) = f x : map' f xs



mapList :: (a -> b) -> List a -> List b
mapList f Nil = Nil
mapList f (Cons x xs) = Cons ( f x)
                        $ mapList f xs


zipWith :: ( a-> b -> c) -> [a] -> [b] -> [c]

--zipWith = undefined


zipWith f [] ys = ys -- не сойдется по типу



zipWith f [] ys = []
zipWith f (x:xs) [] = [] 
zipWith f (x:xs) (y:ys) = 
        (f x y) : (zipWith f xs ys)


zipWith f [] _ = []
zipWith f _ [] = []


foo (Cons x Nil) = error "zero"
foo Nil  = error "one"
--foo (Cons x (Cons y _)) = sumFLoat x y


--foo' (x:y:_) = x + y стандартный список из Prelude



foo' x = case x of
    Nil -> error "zero"
    Cons x xs -> case xs of
        Nil -> error "one"
        Cons y ys -> x + y
