import { PasswordInput } from '@/components/ui/password-input'
import { Button, Card, Container, Field, Heading, Input, Stack, Text } from '@chakra-ui/react'
import React from 'react'

export default function Page() {
  return (
    <Container width='md' pt='24'>
      <Card.Root variant='elevated' boxShadow='lg'>
        <Card.Header gap='1'>
          <Card.Title>회원가입</Card.Title>
          <Card.Description>서비스에 회원가입 합니다.</Card.Description>
        </Card.Header>
        <Card.Body>
          <Stack gap='8'>
            <Stack gap='6'>
              <Field.Root required>
                <Field.Label>ID <Field.RequiredIndicator /></Field.Label>
                <Input />
              </Field.Root>
              <Field.Root required>
                <Field.Label>PW <Field.RequiredIndicator /></Field.Label>
                <PasswordInput />
              </Field.Root>
              <Field.Root required>
                <Field.Label>PW <Field.RequiredIndicator /></Field.Label>
                <PasswordInput />
              </Field.Root>
              <Field.Root>
                <Field.Label>닉네임</Field.Label>
                <Input placeholder='플레이어'/>
              </Field.Root>
              <Field.Root>
                <Field.Label>이메일</Field.Label>
                <Input placeholder='player@gmail.com'/>
              </Field.Root>
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