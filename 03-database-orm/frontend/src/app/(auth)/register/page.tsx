import { PasswordInput } from '@/components/ui/password-input'
import { Button, Card, Container, Field, Heading, Input, RadioCard, RadioGroup, Stack, Text } from '@chakra-ui/react'
import React from 'react'

export default function Page() {
  return (
    <Container width='3xl' pt='24'>
      <Card.Root variant='elevated' boxShadow='lg'>
        <Card.Header gap='1'>
          <Card.Title>회원가입</Card.Title>
          <Card.Description>서비스에 회원가입 합니다.</Card.Description>
        </Card.Header>
        <Card.Body>
          <Stack gap='8'>
            <Stack direction='row' gap='6'>
              <Stack gap='6' width='full'>
                <Field.Root required>
                  <Field.Label>아이디<Field.RequiredIndicator /></Field.Label>
                  <Input />
                </Field.Root>
                <Field.Root required>
                  <Field.Label>비밀번호<Field.RequiredIndicator /></Field.Label>
                  <PasswordInput />
                </Field.Root>
                <Field.Root required>
                  <Field.Label>비밀번호 확인<Field.RequiredIndicator /></Field.Label>
                  <PasswordInput />
                </Field.Root>
              </Stack>
              <Stack gap='6' width='full'>
                <Field.Root required>
                  <Field.Label>닉네임<Field.RequiredIndicator /></Field.Label>
                  <Input placeholder='플레이어'/>
                </Field.Root>
                <Field.Root>
                  <Field.Label>이메일</Field.Label>
                  <Input placeholder='player@gmail.com' type='email'/>
                </Field.Root>
                <Field.Root>
                  <Field.Label>회원 유형</Field.Label>
                  <RadioGroup.Root defaultValue='user' pt='3'>
                    <Stack direction='row'>
                      <RadioGroup.Item value='user'>
                        <RadioGroup.ItemHiddenInput />
                        <RadioGroup.ItemIndicator />
                        <RadioGroup.ItemText>유저</RadioGroup.ItemText>
                      </RadioGroup.Item>
                      <RadioGroup.Item value='admin'>
                        <RadioGroup.ItemHiddenInput />
                        <RadioGroup.ItemIndicator />
                        <RadioGroup.ItemText>관리자</RadioGroup.ItemText>
                      </RadioGroup.Item>
                    </Stack>
                  </RadioGroup.Root>
                </Field.Root>
              </Stack>
            </Stack>
            <Stack gap='4'>
              <Button size='lg'>회원가입</Button>
            </Stack>
          </Stack>
        </Card.Body>
      </Card.Root>
    </Container>
  )
}