{-# LANGUAGE OverloadedStrings, NoImplicitPrelude, RankNTypes #-}

module Main where


import Prelude (Bool(..), undefined, Integer, Num(..))

length :: [a] -> Integer
length [] = 0
length (x:xs) = 1 + length xs

--forall x y. length + length y = length (x + y)
(++) :: forall a . [a] -> [a] -> [a]
(++) [] ys = ys
(++) (x:xs) ys = x:((++) xs ys)

-- concat [a, b, c] == a ++ b ++ c

concat :: forall a. [[a]] -> [a]
concat [] = []
concat (x:xs) = x ++ concat xs

filter :: forall a . (a -> Bool) -> [a] -> [a] 
filter f (x:xs) = if f x then (x:(filter f xs)) 
                  else (filter f xs)

foldl :: (b -> a -> b) -> b -> [a] -> b
foldl f z [] = z
foldl f z (x:xs) = foldl f (f z x) xs

-- f z [a, b, c, d] 
-- f (f z a) [b, c, d] 
-- f (f (f z a) b) [c, d]


foldr :: (a -> b -> b) -> b -> [a] -> b
foldr f z [] = z
foldr f z (x:xs) = f x (foldr f z xs)


--(++) = <- foldr (:)
--[] ++ ys = ys

(+++) :: [a] -> [a] -> [a]
(+++) xs ys = foldr (:) ys xs 

concat' :: forall a. [[a]] -> [a]
concat' xs = foldr (++)  


(++++) :: [a] -> [a] -> [a]
(++++) xs ys = foldr (:) xs ys

map :: (a -> b) -> [a] -> [b]
map f = foldr ((:) . f) []

map' :: (a -> b) -> [a] -> [b]
-- \x xs -> f x : xs
-- \x xs -> (:) f x xs


---------------------------

type Age = Int
type Money = Integer
type DB = [(String, Age, Money)]

head :: [a] -> a
head [] = error "bad"
head (x:_) = x


statistics :: Char -> DB -> Integer
statistics c [] = 0
statistics c ((name, age, money):xs) = 
    if head name == c 
    then money + (statistics c xs)
    else statistics c xs

statistics' :: Char -> DB -> Integer
statistics' c [] = 0
statistics' c xs = foldr (+) 0 
            $ map(\(_, _, x) -> x)
            $ filter (\(name, _, _) -> head name == c)
            $ x

data Person =   Person { name :: String, age::Age, money:: Money}
                deriving Show
type DB' = [Person]

statictics'' :: Char -> DB' -> Integer
statictics'' c = foldr (+) 0
                    . map' money
                    . filter ((== c) . head.name)




statictics''' :: Char -> DB' -> Integer
statictics''' c = (\(n, sum) -> div sum n)
                    . foldl (\(n, sum) m -> (n + 1, sum + m) (0, 0)
                    . map' money
                    . filter ((== c) . head.name)

class Show a where
    show :: a -> String

instance Ord a => Vector2D a where
    compare v1 v2 | (vx v1) < (vx v2) = LT
                    |  ---- GT
                    | ----- EQ

--standart data structures ????



--newtype

data Time  = Time { untime :: Integer }
data Unit  = Second | Hour

second :: Unit
second = Second
hour :: Unit
hour = Hour

instance Num (Unit -> Time) where
    fromInteger n Second = n
    fromInteger n Hour = n * 3600



1 :: Unit -> Time
-- 1 -> fromInteger 1
foo :: Time
foo = 1 second
