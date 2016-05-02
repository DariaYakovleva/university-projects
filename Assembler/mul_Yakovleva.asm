                section         .text

                global          _start
_start:

                sub             rsp, 2 * 128 * 8
                lea             rdi, [rsp + 128 * 8]
                mov             rcx, 128
                call            read_long
             ;mov qword [rdi], 8
                mov             rdi, rsp
                call            read_long
             ;mov qword [rdi], 3
                lea             rsi, [rsp + 128 * 8]
                ;make r8
                                sub             rsp, 128 * 8
                                mov             r8, rsp

                ;make r9
                                sub             rsp, 128 * 8
                                mov             r9, rsp

                ;make r10
                                sub             rsp, 128 * 8
                                mov             r10, rsp
                call            mul_long_long

                call            write_long

                mov             al, 0x0a
                call            write_char

                jmp             exit

; adds two long number
;    rdi -- address of summand #1 (long number)
;    r10 -- address of summand #2 (long number)
;    rcx -- length of long numbers in qwords
; result:
;    sum is written to r10


add_long_long:
                push            rdi
                push            r10
                push            rcx


                clc
                lea             r10, [r10 + 8 * rdx]
.loop:
                mov             rax, [rdi]
                lea             rdi, [rdi + 8]
                adc             [r10], rax
                lea             r10, [r10 + 8]
                dec             rcx
                jnz             .loop

                pop             rcx
                pop             r10
                pop             rdi
                ret

; adds 64-bit number to long number
;    rdi -- address of summand #1 (long number)
;    rax -- summand #2 (64-bit unsigned)
;    rcx -- length of long number in qwords
; result:
;    sum is written to rdi
add_long_short:
                push            rdi
                push            rcx
                push            rdx

                xor             rdx,rdx
.loop:
                add             [rdi], rax
                adc             rdx, 0
                mov             rax, rdx
                xor             rdx, rdx
                add             rdi, 8
                dec             rcx
                jnz             .loop


                pop             rdx
                pop             rcx
                pop             rdi
                ret

; multiplies long number by a short
;    rdi -- address of multiplier #1 (long number)
;    rbx -- multiplier #2 (64-bit unsigned)
;    rcx -- length of long number in qwords
; result:
;    product is written to rdi
mul_long_short:
                push            rax
                push            rdi
                push            rcx

                xor             r12, r12
.loop:
                mov             rax, [rdi]
                mul             rbx
                add             rax, r12
                adc             rdx, 0
                mov             [rdi], rax
                add             rdi, 8
                mov             r12, rdx
                dec             rcx
                jnz             .loop

                pop             rcx
                pop             rdi
                pop             rax
                ret


; multiplies two long number
;    rdi -- address of multiplier #1 (long number)
;    rsi -- ddress of multiplier #2 (long number)
;    rcx -- length of long number in qwords
; result:
;    product is written to rdi

mul_long_long:


;copy rdi to r8
                push            rdi
                push            r8
                push            rdx
                push            rax

                mov             rdx, 128
.copy_rdi_r8:
                mov             rax, [rdi]
                mov             [r8], rax
                lea             r8, [r8 + 8]
                lea             rdi, [rdi + 8]
                dec             rdx
                jnz             .copy_rdi_r8

                pop             rax
                pop             rdx
                pop             r8
                pop             rdi
;end of copy rdi to r8

                push            rdi
                push            rsi
                push            rbx
                push            rcx

                mov             rcx, 128
.loop:
                mov             rbx, [rsi]
                lea             rsi, [rsi + 8]

                call            mul_long_short

;shift
                xor             rdx, rdx
                sub             rdx, rcx
                add             rdx, 128
;shift_end
                call            add_long_long

;copy r8 to rdi

                push            rdi
                push            r8
                push            rdx
                mov             rdx, 128
.copy_r8_rdi:
                mov             rax, [r8]
                mov             [rdi], rax
                lea             rdi, [rdi + 8]
                lea             r8, [r8 + 8]
                dec             rdx
                jnz             .copy_r8_rdi

                pop             rdx
                pop             r8
                pop             rdi
;end of copy r8 to rdi

                dec             rcx
                jnz             .loop

;copy r10ans to rdi

                push            rdi
                push            r10
                push            rdx
                mov             rdx, 128
.copy_r10_rdi:
                mov             rax, [r10]
                mov             [rdi], rax
                lea             rdi, [rdi + 8]
                lea             r10, [r10 + 8]
                dec             rdx
                jnz             .copy_r10_rdi

                pop             rdx
                pop             r10
                pop             rdi
;end of copy r10ans to rdi

                pop             rcx
                pop             rbx
                pop             rsi
                pop             rdi
                ret



; divides long number by a short
;    rdi -- address of dividend (long number)
;    rbx -- divisor (64-bit unsigned)
;    rcx -- length of long number in qwords
; result:
;    quotient is written to rdi
;    rdx -- remainder
div_long_short:
                push            rdi
                push            rax
                push            rcx

                lea             rdi, [rdi + 8 * rcx - 8]
                xor             rdx, rdx

.loop:
                mov             rax, [rdi]
                div             rbx
                mov             [rdi], rax
                sub             rdi, 8
                dec             rcx
                jnz             .loop

                pop             rcx
                pop             rax
                pop             rdi
                ret

; assigns a zero to long number
;    rdi -- argument (long number)
;    rcx -- length of long number in qwords
set_zero:
                push            rax
                push            rdi
                push            rcx

                xor             rax, rax
                rep stosq

                pop             rcx
                pop             rdi
                pop             rax
                ret

; checks if a long number is a zero
;    rdi -- argument (long number)
;    rcx -- length of long number in qwords
; result:
;    ZF=1 if zero
is_zero:
                push            rax
                push            rdi
                push            rcx

                xor             rax, rax
                rep scasq

                pop             rcx
                pop             rdi
                pop             rax
                ret

; read long number from stdin
;    rdi -- location for output (long number)
;    rcx -- length of long number in qwords
read_long:
                push            rcx
                push            rdi

                call            set_zero
.loop:
                call            read_char
                or              rax, rax
                js              exit
                cmp             rax, 0x0a
                je              .done
                cmp             rax, '0'
                jb              .invalid_char
                cmp             rax, '9'
                ja              .invalid_char

                sub             rax, '0'
                mov             rbx, 10
                call            mul_long_short
                call            add_long_short
                jmp             .loop

.done:
                pop             rdi
                pop             rcx
                ret

.invalid_char:
                mov             rsi, invalid_char_msg
                mov             rdx, invalid_char_msg_size
                call            print_string
                call            write_char
                mov             al, 0x0a
                call            write_char

.skip_loop:
                call            read_char
                or              rax, rax
                js              exit
                cmp             rax, 0x0a
                je              exit
                jmp             .skip_loop

; write long number to stdout
;    rdi -- argument (long number)
;    rcx -- length of long number in qwords
write_long:
                push            rax
                push            rcx

                mov             rax, 20
                mul             rcx
                mov             rbp, rsp
                sub             rsp, rax

                mov             rsi, rbp

.loop:
                mov             rbx, 10
                call            div_long_short
                add             rdx, '0'
                dec             rsi
                mov             [rsi], dl
                call            is_zero
                jnz             .loop

                mov             rdx, rbp
                sub             rdx, rsi
                call            print_string

                mov             rsp, rbp
                pop             rcx
                pop             rax
                ret

; read one char from stdin
; result:
;    rax == -1 if error occurs
;    rax \in [0; 255] if OK
read_char:
                push            rcx
                push            rdi

                sub             rsp, 1
                xor             rax, rax
                xor             rdi, rdi
                mov             rsi, rsp
                mov             rdx, 1
                syscall

                cmp             rax, 1
                jne             .error
                xor             rax, rax
                mov             al, [rsp]
                add             rsp, 1

                pop             rdi
                pop             rcx
                ret
.error:
                mov             rax, -1
                add             rsp, 1
                pop             rdi
                pop             rcx
                ret

; write one char to stdout, errors are ignored
;    al -- char
write_char:
                sub             rsp, 1
                mov             [rsp], al

                mov             rax, 1
                mov             rdi, 1
                mov             rsi, rsp
                mov             rdx, 1
                syscall
                add             rsp, 1
                ret

exit:
                mov             rax, 60
                xor             rdi, rdi
                syscall

; print string to stdout
;    rsi -- string
;    rdx -- size
print_string:
                push            rax

                mov             rax, 1
                mov             rdi, 1
                syscall

                pop             rax
                ret


                section         .rodata
invalid_char_msg:
                db              "Invalid character: "
invalid_char_msg_size: equ             $ - invalid_char_msg
