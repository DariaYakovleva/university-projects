{-#LANGUAGE UnicodeSyntax, OverloadedStrings, NoImplicitePrelude, RankNTypes, FlexibleInstances #-}

module Lesson where

-- _()[a-z]+*,-

import Prelude


type Con = Int
type Var = String
data Expr = Const CN
	 | Var VN
	 | Lambda VN Expr
	 | App Expr Expr
	deriving show
-- lambda x . x
example = Lambda "x" $ Var "x"

data Maybe a = Nothing | Just a

type Ctx = VN -> Maybe Expr
type G = [ (VN, Type) ]

extend :: Ctx -> VN -> Expr -> Ctx
extend c n e n' = if n == n' then Just e else c n'

eval :: Ctx -> Expr -> Expr
eval c (Const n) = n
eval c (Var n) = case c n of
   Nothing -> error "oops"
   Just e -> e
eval c (Lambda n e) = Lambda n e
eval c (App (Lambda n a) b) = eval (extend c n b) a   

example = Lambda "x" $ Var "x"
example' = App example (Lambda "y" $ Var "y")
example'' = App example (App (Lambda "y" $ Var "y") (Const 1))


data Arith = Const Int
	   | Sum {left :: Arith, right :: Arith}
	   | Mul Arith Arith
	   | Sub Arith Arith
	   | Div Arith Arith

eval :: Arith -> Int
eval (Const i) = i
eval (Sum a b) = eval a + eval b
--...

test = Const 1 `Sum` Const 2
teste = eval test
--1998 sorcnsin /urzyczyn Lection ...
--A = Int | + A A | - A A | ..
--lambda = V | lambda V . lambda | lambda @ lambda
--V = a | b | c | ..
--T = Int | t -> T
--G = [(lambda, t), (,)...]
--G = G, V:t | E
--omega = G |- lambda : t
--Sample a::Int, b::Int |- b : Int -> Int

--
--
