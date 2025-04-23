'use client'

import { ActionBar, Badge, Button, Checkbox, CloseButton, Container, Dialog, Field, FieldRequiredIndicator, Fieldset, Heading, Icon, Input, InputGroup, Portal, RadioGroup, Separator, Stack, Table, Text } from '@chakra-ui/react'
import React, { useState } from 'react'
import { FaKey, FaSearch } from 'react-icons/fa'
import { LuSquareDashed, LuPencil, LuTrash2, LuPlus, LuRefreshCcw } from 'react-icons/lu'
import { getDaysDifference } from '@/utils/date'
import { Toaster, toaster } from "@/components/ui/toaster"

const CheckoutManagePage: React.FC = () => {

  const checkoutData = [
    { idx: 1, memberName: '이승훈', bookTitle: '스프링 6와 스프링 부트 3로 배우는 모던 API 개발', inDate: '2025-04-23', outDate: '2025-04-23' }
  ]

  const [ selection, setSelection ] = useState<number[]>([])
  const hasSelection = selection.length > 0
  const indeterminate = hasSelection && selection.length < checkoutData.length

  const rows = checkoutData.map((data) => {
    const diffDay = getDaysDifference(data.outDate)
    return (
      <Table.Row key={data.idx} data-selected={selection.includes(data.idx) ? "" : undefined}>
        <Table.Cell>
          <Checkbox.Root
            size='sm'
            top='0.5'
            checked={selection.includes(data.idx)}
            onCheckedChange={(changes) => {
              setSelection((prev) =>
                changes.checked
                  ? [...prev, data.idx]
                  : selection.filter((isbn) => isbn !== data.idx)
              )
            }}
          >
            <Checkbox.HiddenInput />
            <Checkbox.Control />
          </Checkbox.Root>
        </Table.Cell>
        <Table.Cell>{data.idx}</Table.Cell>
        <Table.Cell>{data.memberName}</Table.Cell>
        <Table.Cell>{data.bookTitle}</Table.Cell>
        <Table.Cell>{data.inDate}</Table.Cell>
        <Table.Cell>{data.outDate}</Table.Cell>
        <Table.Cell>D{diffDay <= 0 ? '-' : '+'}{Math.abs(diffDay)}</Table.Cell>
        <Table.Cell><Badge colorPalette={diffDay <= 0 ? 'green' : 'red'}>{diffDay <= 0 ? '정상' : '연체'}</Badge></Table.Cell>
        <Table.Cell>
          <Stack direction='row' gap='3'>
            <Button size='xs' onClick={() => toaster.create({
              title: '반납 처리 완료',
              type: 'success'
            })}>반납</Button>
            <Button size='xs'>연장</Button>
          </Stack>
        </Table.Cell>
      </Table.Row>
    )
  })

  const getPlaceholder = () => {
    switch (searchMethod) {
      case 'member':
        return '대출자'
      case 'title':
        return '제목'
      default:
        return ''
    }
  }

  const [ searchMethod, setSearchMethod ] = useState('title')

  return (
    <Container>
      <Toaster />
      <Stack>
        <Stack mt='12' gap='6'>
          <Heading as='h1' size='4xl' fontWeight='bold'>대출 관리</Heading>
          <Stack direction='row' justifyContent='space-between' alignItems='end'>
            <Stack direction='row' borderRadius='l3' borderWidth='1px' p='6'>
              <Field.Root>
                <Field.Label>키워드 검색</Field.Label>
                <InputGroup startElement={<FaSearch />}>
                  <Input width='400px' variant='subtle' placeholder={getPlaceholder()}/>
                </InputGroup>
                <RadioGroup.Root pt='2' ps='1' value={searchMethod} onValueChange={(e) => setSearchMethod(e.value!)}>
                  <Stack direction='row' gap='6'>
                    <RadioGroup.Item value='member'>
                      <RadioGroup.ItemHiddenInput />
                      <RadioGroup.ItemIndicator />
                      <RadioGroup.ItemText>대출자</RadioGroup.ItemText>
                    </RadioGroup.Item>
                    <RadioGroup.Item value='title'>
                      <RadioGroup.ItemHiddenInput />
                      <RadioGroup.ItemIndicator />
                      <RadioGroup.ItemText>제목</RadioGroup.ItemText>
                    </RadioGroup.Item>
                  </Stack>
                </RadioGroup.Root>
              </Field.Root>
            </Stack>
            <Stack direction='row' gap='4'>
              <Button variant='outline'><LuRefreshCcw />새로고침</Button>
            </Stack>
          </Stack>
          <Text fontSize='sm' color='fg.muted' pl='6'>총 7권 검색됨</Text>
          <Table.Root interactive>
            <Table.ColumnGroup>
              <Table.Column htmlWidth='6'/>
              <Table.Column htmlWidth='5%'/>
              <Table.Column htmlWidth='10%'/>
              <Table.Column />
              <Table.Column htmlWidth='10%'/>
              <Table.Column htmlWidth='10%'/>
              <Table.Column htmlWidth='7%'/>
              <Table.Column htmlWidth='7%'/>
              <Table.Column htmlWidth='11%'/>
            </Table.ColumnGroup>
            <Table.Header>
              <Table.Row>
                <Table.ColumnHeader>
                  <Checkbox.Root
                    size='sm'
                    top='0.5'
                    checked={indeterminate ? 'indeterminate' : selection.length > 0}
                    onCheckedChange={(changes) => {
                      setSelection(changes.checked ? checkoutData.map((data) => data.idx) : [])
                    }}
                  >
                    <Checkbox.HiddenInput />
                    <Checkbox.Control />
                  </Checkbox.Root>
                </Table.ColumnHeader>
                <Table.ColumnHeader>순번</Table.ColumnHeader>
                <Table.ColumnHeader>대출자</Table.ColumnHeader>
                <Table.ColumnHeader>책 제목</Table.ColumnHeader>
                <Table.ColumnHeader>대출일</Table.ColumnHeader>
                <Table.ColumnHeader>반납일</Table.ColumnHeader>
                <Table.ColumnHeader>남은 일수</Table.ColumnHeader>
                <Table.ColumnHeader>상태</Table.ColumnHeader>
                <Table.ColumnHeader>작업</Table.ColumnHeader>
              </Table.Row>
            </Table.Header>
            <Table.Body>{rows}</Table.Body>
          </Table.Root>
          <ActionBar.Root open={hasSelection}>
            <Portal>
              <ActionBar.Positioner>
                <ActionBar.Content>
                  <ActionBar.SelectionTrigger>
                    {selection.length} 권 선택됨
                  </ActionBar.SelectionTrigger>
                  <ActionBar.Separator />
                  <Button variant='outline' onClick={() => setSelection([])}><LuSquareDashed />선택해제</Button>
                  <Button variant='outline' disabled={selection.length != 1}><LuPencil />편집</Button>
                  <Button colorPalette='red' variant='surface'><LuTrash2 />삭제</Button>
                </ActionBar.Content>
              </ActionBar.Positioner>
            </Portal>
          </ActionBar.Root>
        </Stack>
      </Stack>
    </Container>
  )
}

export default CheckoutManagePage